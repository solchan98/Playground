package org.example.springsecurityjwt.refresh;

import org.example.springsecurityjwt.refresh.RefreshAuthenticationToken;

public interface RefreshTokenRepository {

    void save(RefreshAuthenticationToken authenticationToken, long ttl);

    boolean existsByToken(RefreshAuthenticationToken authenticationToken);
}
