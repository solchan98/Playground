package org.example.springjwt.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.example.springjwt.auth.Role;
import org.example.springjwt.auth.Roles;
import org.example.springjwt.auth.User;
import org.example.springjwt.auth.UserRepository;
import org.example.springjwt.db.InMemoryUserRepository;
import org.example.springjwt.db.Storage;
import org.example.springjwt.db.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryUserRepositoryTest {

    UserRepository userRepository = new InMemoryUserRepository();

    @BeforeEach
    void clearStorage() {
        Storage.clearAll();
    }

    @Test
    void save() {
        // given
        User user = new User(1L, "sol", "sol@sol.com", "encrypedPassword", new Roles(Collections.singleton(Role.READ_ONLY)));

        // when
        User result = userRepository.save(user);

        // then
        assertThat(result).isEqualTo(user);
    }

    @Test
    void findById() {
        // given
        UserEntity userEntity = new UserEntity(1L, "sol", "sol@sol.com","encrypedPassword", List.of(Role.READ_ONLY.name()));
        Storage.users.put(userEntity.getId(), userEntity);
        User expected = userEntity.toUser();

        // when
        Optional<User> result = userRepository.findById(userEntity.getId());

        // then
        assertThat(result).contains(expected);
    }

    @Test
    void findByEmail() {
        // given
        UserEntity userEntity = new UserEntity(1L, "sol", "sol@sol.com","encrypedPassword", List.of(Role.READ_ONLY.name()));
        Storage.users.put(userEntity.getId(), userEntity);
        User expected = userEntity.toUser();

        // when
        Optional<User> result = userRepository.findByEmail(userEntity.getEmail());

        // then
        assertThat(result).contains(expected);
    }

    @Test
    void existsById() {
        // given
        UserEntity userEntity = new UserEntity(1L, "sol", "sol@sol.com", "encrypedPassword", List.of(Role.READ_ONLY.name()));
        Storage.users.put(userEntity.getId(), userEntity);

        // when
        boolean resultTure = userRepository.existById(userEntity.getId());
        boolean resultFalse = userRepository.existById(-1);

        // then
        assertThat(resultTure).isTrue();
        assertThat(resultFalse).isFalse();
    }

    @Test
    void existsByEmail() {
        // given
        UserEntity userEntity = new UserEntity(1L, "sol", "sol@sol.com", "encrypedPassword", List.of(Role.READ_ONLY.name()));
        Storage.users.put(userEntity.getId(), userEntity);
        Storage.userIdByEmail.put(userEntity.getEmail(), userEntity.getId());

        // when
        boolean resultTure = userRepository.existByEmail(userEntity.getEmail());
        boolean resultFalse = userRepository.existByEmail("x");

        // then
        assertThat(resultTure).isTrue();
        assertThat(resultFalse).isFalse();
    }
}
