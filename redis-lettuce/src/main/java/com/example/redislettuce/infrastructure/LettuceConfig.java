package com.example.redislettuce.infrastructure;

import com.example.redislettuce.common.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class LettuceConfig {

    @Bean
    public RedisTemplate<String, Cacheable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Cacheable> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 키는 StringRedisSerializer로 직렬화
        template.setKeySerializer(new StringRedisSerializer());

        /* 값은 GenericJackson2JsonRedisSerializer로 직렬화 (JSON 형식으로 저장)
        * 다만, 클래스의 패키지 위치 변경 시에는 기존 캐시 데이터는 모두 클리어 필요
        * */
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 해시 키와 값도 직렬화 설정
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }

}
