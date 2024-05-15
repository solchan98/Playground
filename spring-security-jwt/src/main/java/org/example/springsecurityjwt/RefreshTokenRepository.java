package org.example.springsecurityjwt;

import org.example.springsecurityjwt.refresh.RefreshAuthenticationToken;

public interface RefreshTokenRepository {

    void save(RefreshAuthenticationToken authenticationToken, long ttl);

    boolean existsByToken(RefreshAuthenticationToken authenticationToken);
}
