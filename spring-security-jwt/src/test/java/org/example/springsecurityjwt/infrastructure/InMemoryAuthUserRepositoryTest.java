package org.example.springsecurityjwt.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.example.springsecurityjwt.auth.AuthUserDetails;
import org.junit.jupiter.api.Test;

class InMemoryAuthUserRepositoryTest {

    InMemoryAuthUserRepository inMemoryAuthUserRepository = new InMemoryAuthUserRepository();

    @Test
    void findById() {
        // given
        UserEntity testUser = new UserEntity(999L, "test1@sol.com", "test user1", "a1234567!******", List.of());
        Storage.users.put(testUser.getId(), testUser);
        AuthUserDetails expected = testUser.toAuthUserDetails();

        // when then
        assertThat(inMemoryAuthUserRepository.findById(testUser.getId()))
                .contains(expected);
        assertThat(inMemoryAuthUserRepository.findById(-1L)).isEmpty();
    }

    @Test
    void findByEmail() {
        // given
        UserEntity testUser = new UserEntity(999L, "test1@sol.com", "test user1", "a1234567!******", List.of());
        Storage.users.put(testUser.getId(), testUser);
        Storage.userIdByEmail.put(testUser.getEmail(), testUser.getId());
        AuthUserDetails expected = testUser.toAuthUserDetails();

        // when then
        assertThat(inMemoryAuthUserRepository.findByEmail(testUser.getEmail()))
                .contains(expected);
        assertThat(inMemoryAuthUserRepository.findByEmail("x")).isEmpty();
    }

    @Test
    void existById() {
        // given
        UserEntity testUser = new UserEntity(999L, "test1@sol.com", "test user1", "a1234567!******", List.of());
        Storage.users.put(testUser.getId(), testUser);

        // when then
        assertThat(inMemoryAuthUserRepository.existById(testUser.getId())).isTrue();
        assertThat(inMemoryAuthUserRepository.existById(-1)).isFalse();
    }

    @Test
    void existByEmail() {
        // given
        UserEntity testUser = new UserEntity(999L, "test1@sol.com", "test user1", "a1234567!******", List.of());
        Storage.users.put(testUser.getId(), testUser);
        Storage.userIdByEmail.put(testUser.getEmail(), testUser.getId());

        // when then
        assertThat(inMemoryAuthUserRepository.existByEmail(testUser.getEmail())).isTrue();
        assertThat(inMemoryAuthUserRepository.existByEmail("x")).isFalse();
    }
}
