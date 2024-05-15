package org.example.springsecurityjwt.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class RedisRefreshTokenRepositoryTest {

    RedisClient redisClient = mock();

    RedisRefreshTokenRepository refreshTokenRepository = new RedisRefreshTokenRepository(redisClient);

    @Test
    void existsByToken() {
        // given
        String token = "token";
        String invalidToken = "invalidToken";
        given(redisClient.getValues(token)).willReturn("sol");
        given(redisClient.getValues(invalidToken)).willReturn(null);

        // when then
        assertThat(refreshTokenRepository.existsByToken(token)).isTrue();
        assertThat(refreshTokenRepository.existsByToken(invalidToken)).isFalse();

    }

}
