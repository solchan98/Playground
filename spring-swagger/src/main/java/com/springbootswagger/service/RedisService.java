package com.springbootswagger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    // 키-벨류 설정
    public void setValues(String token, String email){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        values.set(name, age);
        values.set(token, email, Duration.ofMinutes(3));  // 3분 뒤 메모리에서 삭제된다.
    }

    // 키값으로 벨류 가져오기
    public String getValues(String token){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(token);
    }

    // 키-벨류 삭제
    public void delValues(String token) {
        redisTemplate.delete(token.substring(7));
    }
}
