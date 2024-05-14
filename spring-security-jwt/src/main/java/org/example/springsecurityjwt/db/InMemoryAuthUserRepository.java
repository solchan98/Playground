package org.example.springsecurityjwt.db;

import java.util.Optional;
import org.example.springsecurityjwt.common.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class InMemoryAuthUserRepository implements AuthUserRepository {

    @Override
    public Optional<UserDetails> findById(long id) {
        UserEntity userEntity = Storage.users.get(id);
        if (userEntity == null) {
            return Optional.empty();
        }

        return Optional.of(userEntity);
    }

    @Override
    public Optional<UserDetails> findByEmail(String email) {
        Long userId = Storage.userIdByEmail.get(email);
        if (userId == null) {
            return Optional.empty();
        }

        return findById(userId);
    }

    @Override
    public boolean existById(long id) {
        return Storage.users.containsKey(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return Storage.userIdByEmail.containsKey(email);
    }
}
