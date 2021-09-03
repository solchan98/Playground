package redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void setValues(String name, String age){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        values.set(name, age);
        values.set(name, age, Duration.ofMinutes(1));  // 1분 뒤 메모리에서 삭제된다.
    }

    public String getValues(String name){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(name);
    }
}
