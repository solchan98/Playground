package com.example.redislettuce.infrastructure;

import com.example.redislettuce.common.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheMaster {

    private final RedisTemplate<String, Cacheable> redisTemplate;

    public void clear(String key) {
        redisTemplate.delete(key);
    }
}
