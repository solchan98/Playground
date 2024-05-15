package org.example.springsecurityjwt.db;

import java.time.LocalDateTime;
import org.example.springsecurityjwt.refresh.RefreshTokenRepository;
import org.example.springsecurityjwt.refresh.RefreshAuthenticationToken;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRefreshTokenRepository implements RefreshTokenRepository {

    @Override
    public void save(RefreshAuthenticationToken authenticationToken, long ttl) {
        Storage.refreshTokens.put(authenticationToken.getEmail(), authenticationToken.getName());
        LocalDateTime expireTime = LocalDateTime.now()
                .plusNanos(ttl);
        Storage.refreshTokenTtl.put(authenticationToken.getName(), expireTime);
    }

    @Override
    public boolean existsByToken(RefreshAuthenticationToken authenticationToken) {
        String token = Storage.refreshTokens.get(authenticationToken.getName());
        if (token == null) {
            return false;
        }

        LocalDateTime localDateTime = Storage.refreshTokenTtl.get(token);
        return !localDateTime.isBefore(LocalDateTime.now());
    }
}
