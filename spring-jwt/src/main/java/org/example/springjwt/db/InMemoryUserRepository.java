package org.example.springjwt.db;

import java.util.Optional;
import org.example.springjwt.auth.User;
import org.example.springjwt.auth.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserRepository implements UserRepository {

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.from(user);
        Storage.users.put(userEntity.getId(), userEntity);
        Storage.userIdByEmail.put(userEntity.getEmail(), userEntity.getId());

        return userEntity.toUser();
    }

    @Override
    public Optional<User> findById(long id) {
        UserEntity userEntity = Storage.users.get(id);
        if (userEntity == null) {
            return Optional.empty();
        }

        return Optional.of(userEntity.toUser());
    }

    @Override
    public Optional<User> findByEmail(String email) {
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
