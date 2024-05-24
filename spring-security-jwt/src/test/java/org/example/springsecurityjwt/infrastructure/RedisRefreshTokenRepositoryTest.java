package org.example.springsecurityjwt.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class RedisRefreshTokenRepositoryTest {

    RedisClient redisClient = mock();

    RedisRefreshTokenRepository redisRefreshTokenRepository = new RedisRefreshTokenRepository(redisClient);

    @Test
    void findByEmail() {
        // given
        String email = "sol@sol.com";
        String token = "token";
        given(redisClient.getValues(email)).willReturn(token);

        // when then
        assertThat(redisRefreshTokenRepository.findByEmail(email)).contains(token);
    }

    @Test
    void save() {
        // given
        String email = "sol@sol.com";
        String token = "token";
        long ttl = 1000 * 60; // 1M
        redisRefreshTokenRepository.save(email, token, ttl);

        // when then
        verify(redisClient, times(1)).setValues(email, token, Duration.ofMillis(ttl));
    }
}
