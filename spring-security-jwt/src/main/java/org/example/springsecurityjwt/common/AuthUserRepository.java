package org.example.springsecurityjwt.common;

import java.util.Optional;

public interface AuthUserRepository {

    Optional<AuthUser> findById(long id);

    Optional<AuthUser> findByEmail(String email);

    boolean existById(long id);

    boolean existByEmail(String email);
}
