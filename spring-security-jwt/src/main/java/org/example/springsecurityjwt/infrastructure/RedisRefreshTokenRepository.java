package org.example.springsecurityjwt.infrastructure;

import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.refresh.RefreshTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRefreshTokenRepository implements RefreshTokenRepository {

    private final RedisClient redisClient;

    @Override
    public void save(String email, String token, long ttl) {
        redisClient.setValues(email, token, Duration.ofMillis(ttl));
    }

    @Override
    public Optional<String> findByEmail(String email) {
        return Optional.ofNullable(redisClient.getValues(email));
    }
}
