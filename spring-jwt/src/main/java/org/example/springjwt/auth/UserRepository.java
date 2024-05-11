package org.example.springjwt.auth;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    boolean existById(long id);

    boolean existByEmail(String email);
}
