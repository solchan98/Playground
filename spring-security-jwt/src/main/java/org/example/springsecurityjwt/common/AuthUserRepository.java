package org.example.springsecurityjwt.common;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserRepository {

    Optional<UserDetails> findById(long id);

    Optional<UserDetails> findByEmail(String email);

    boolean existById(long id);

    boolean existByEmail(String email);
}
