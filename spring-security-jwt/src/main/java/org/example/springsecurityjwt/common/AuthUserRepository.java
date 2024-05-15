package org.example.springsecurityjwt.common;

import java.util.Optional;

public interface AuthUserRepository {

    Optional<AuthUserDetails> findById(long id);

    Optional<AuthUserDetails> findByEmail(String email);

    boolean existById(long id);

    boolean existByEmail(String email);
}
