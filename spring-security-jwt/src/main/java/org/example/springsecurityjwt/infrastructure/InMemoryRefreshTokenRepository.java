package org.example.springsecurityjwt.infrastructure;

import java.time.LocalDateTime;
import java.util.Optional;
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
    public Optional<String> findByEmail(String email) {
        boolean contains = Storage.refreshTokens.containsKey(email);
        if (!contains) {
            return Optional.empty();
        }

        return Optional.of(Storage.refreshTokens.get(email));
    }
}
