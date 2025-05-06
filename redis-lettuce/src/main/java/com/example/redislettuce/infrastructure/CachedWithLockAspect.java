package com.example.redislettuce.infrastructure;

import com.example.redislettuce.common.Cacheable;
import com.example.redislettuce.domain.Book;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class CachedWithLockAspect {

    private final RedisTemplate<String, Cacheable> redisTemplate;
    private final RedissonClient redissonClient;

    @Around("@annotation(cachedWithLock)")
    public Object handle(ProceedingJoinPoint joinPoint, CachedWithLock cachedWithLock) throws Throwable {
//        String prefix = cachedWithLock.prefix();
        String cacheKey = generateKey(joinPoint.getArgs());
        String lockKey = "lock:" + cacheKey;

        // 1. 캐시 조회
        Cacheable cachedValue = redisTemplate.opsForValue().get(cacheKey);
        if (cachedValue instanceof Book book) {
            return Optional.of(book);
        }

        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = false;

        try {
            locked = lock.tryLock(
                    cachedWithLock.lockWait(),
                    cachedWithLock.lockLease(),
                    TimeUnit.MILLISECONDS
            );

            if (locked) {
                // 2. 락을 잡았으면 캐시 다시 확인
                Cacheable recheck = redisTemplate.opsForValue().get(cacheKey);
                if (recheck instanceof Book book) {
                    return Optional.of(book);
                }

                // 3. 원본 메서드 실행 (DB 호출)
                Optional proceed = (Optional) joinPoint.proceed();
                if (proceed.isPresent()) {
                    redisTemplate.opsForValue().set(cacheKey, (Book) proceed.get());
                }


                return proceed;
            } else {
                // 5. 락 못 잡으면 retry wait 대기 후 재시도
                Thread.sleep(cachedWithLock.retryWait());
                return redisTemplate.opsForValue().get(cacheKey);
            }

        } finally {
            if (locked) lock.unlock();
        }
    }

    private String generateKey(Object[] args) {
        return Arrays.stream(args)
                .map(String::valueOf)
                .collect(Collectors.joining(":"));
    }
}

