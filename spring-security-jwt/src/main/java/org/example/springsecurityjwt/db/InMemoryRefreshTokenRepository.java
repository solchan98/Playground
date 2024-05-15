package org.example.springsecurityjwt.db;

import java.time.LocalDateTime;
import org.example.springsecurityjwt.refresh.RefreshTokenRepository;

public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {

    @Override
    public void save(String email, String token, long ttl) {
        Storage.refreshTokens.put(email, token);
        LocalDateTime expireTime = LocalDateTime.now()
                .plusNanos(ttl);
        Storage.refreshTokenTtl.put(token, expireTime);
    }

    @Override
    public boolean existsByToken(String token) {
        String value = Storage.refreshTokens.get(token);
        if (value == null) {
            return false;
        }

        LocalDateTime localDateTime = Storage.refreshTokenTtl.get(value);
        return !localDateTime.isBefore(LocalDateTime.now());
    }
}
