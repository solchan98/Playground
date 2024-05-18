package org.example.springsecurityjwt.infrastructure;

import io.netty.util.internal.StringUtil;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.refresh.RefreshTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRefreshTokenRepository implements RefreshTokenRepository {

    private final RedisClient redisClient;

    @Override
    public void save(String email, String token, long ttl) {
        redisClient.setValues(token, email, Duration.ofMillis(ttl));
    }

    @Override
    public boolean existsByToken(String token) {
        return !StringUtil.isNullOrEmpty(redisClient.getValues(token));
    }
}
