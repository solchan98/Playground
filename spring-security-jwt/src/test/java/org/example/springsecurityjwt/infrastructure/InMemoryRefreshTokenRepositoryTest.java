package org.example.springsecurityjwt.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InMemoryRefreshTokenRepositoryTest {

    InMemoryRefreshTokenRepository inMemoryRefreshTokenRepository = new InMemoryRefreshTokenRepository();

    @Test
    void findByEmail() {
        // given
        String email = "sol@sol.com";
        String token = "token";
        long ttl = 1000; // 1s
        inMemoryRefreshTokenRepository.save(email, token, ttl);

        // when then
        assertThat(inMemoryRefreshTokenRepository.findByEmail(email)).contains(token);
    }

    @Test
    void save() {
        // given
        String email = "sol@sol.com";
        String token = "token";
        long ttl = 1000 * 60; // 1M
        inMemoryRefreshTokenRepository.save(email, token, ttl);

        // when then
        assertThat(inMemoryRefreshTokenRepository.findByEmail(email)).contains(token);
    }

    @Test
    void expire_test() throws Exception {
        // given
        String email = "sol@sol.com";
        String token = "token";
        long ttl = 500;
        inMemoryRefreshTokenRepository.save(email, token, ttl);
        Thread.sleep(ttl + 300);

        // when then
        assertThat(inMemoryRefreshTokenRepository.findByEmail(email)).isEmpty();
    }
}
