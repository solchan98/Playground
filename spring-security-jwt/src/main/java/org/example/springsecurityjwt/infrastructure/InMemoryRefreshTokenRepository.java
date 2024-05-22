package org.example.springsecurityjwt.infrastructure;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.example.springsecurityjwt.refresh.RefreshTokenRepository;

public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {

    @Override
    public void save(String email, String token, long ttl) {
        Storage.refreshTokens.put(email, token);
        LocalDateTime expireTime = LocalDateTime.now()
                .plus(ttl, ChronoUnit.MILLIS);
        Storage.refreshTokenTtl.put(token, expireTime);
    }

    @Override
    public Optional<String> findByEmail(String email) {
        boolean contains = Storage.refreshTokens.containsKey(email);
        if (!contains) {
            return Optional.empty();
        }

        String token = Storage.refreshTokens.get(email);
        LocalDateTime expiredTime = Storage.refreshTokenTtl.get(token);
        if (LocalDateTime.now().isAfter(expiredTime)) {
            return Optional.empty();
        }

        return Optional.of(token);
    }
}
