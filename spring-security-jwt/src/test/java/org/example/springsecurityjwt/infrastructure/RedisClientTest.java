package org.example.springsecurityjwt.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.example.springsecurityjwt.config.EmbeddedRedisConfig;
import org.example.springsecurityjwt.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;

@DataRedisTest
@Import(value = {EmbeddedRedisConfig.class, RedisConfig.class, RedisClient.class})
class RedisClientTest {

    @Autowired
    RedisClient redisClient;

    @Test
    void setValues() {
        // given
        String key = "name";
        String data = "sol";

        // when
        redisClient.setValues(key, data);

        // then
        assertThat(redisClient.getValues(key)).isEqualTo(data);
    }

    @Test
    void setValues_with_duration() throws InterruptedException {
        // given
        String key = "name";
        String data = "sol";
        Duration duration = Duration.ofMillis(300);

        // when
        redisClient.setValues(key, data, duration);
        Thread.sleep(500);

        // then
        assertThat(redisClient.getValues(key)).isNull();
    }

    @Test
    void deleteValues() {
        // given
        String key = "name";
        String data = "sol";
        redisClient.setValues(key, data);

        // when
        redisClient.deleteValues(key);


        // then
        assertThat(redisClient.getValues(key)).isNull();
    }
}
