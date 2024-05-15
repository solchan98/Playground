package org.example.springsecurityjwt.refresh;

public interface RefreshTokenRepository {

    void save(String email, String token, long ttl);

    boolean existsByToken(String token);
}
