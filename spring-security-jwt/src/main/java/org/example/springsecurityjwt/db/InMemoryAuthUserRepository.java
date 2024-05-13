package org.example.springsecurityjwt.db;

import java.util.Optional;
import org.example.springsecurityjwt.common.AuthUser;
import org.example.springsecurityjwt.common.AuthUserRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryAuthUserRepository implements AuthUserRepository {

    @Override
    public Optional<AuthUser> findById(long id) {
        UserEntity userEntity = Storage.users.get(id);
        if (userEntity == null) {
            return Optional.empty();
        }

        return Optional.of(userEntity.toUser());
    }

    @Override
    public Optional<AuthUser> findByEmail(String email) {
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
