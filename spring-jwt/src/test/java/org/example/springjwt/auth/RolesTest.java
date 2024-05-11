package org.example.springjwt.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.springjwt.auth.Role.ADMIN;
import static org.example.springjwt.auth.Role.READ_ONLY;

import java.util.Collections;
import org.example.springjwt.auth.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RolesTest {

    @Test
    @DisplayName("권한이 존재하는지 확인할 수 있다.")
    void isContainsRole() {
        Roles roles = new Roles(Collections.singleton(READ_ONLY));

        assertThat(roles.isContainsRole(READ_ONLY)).isTrue();
        assertThat(roles.isContainsRole(ADMIN)).isFalse();
    }

    @Test
    @DisplayName("권한이 하나라도 존재하는지 확인할 수 있다.")
    void isContainsAny() {
        Roles roles = new Roles(Collections.singleton(READ_ONLY));

        assertThat(roles.isContainsAny(new Roles(Collections.singleton(READ_ONLY)))).isTrue();
        assertThat(roles.isContainsAny(new Roles(Collections.singleton(ADMIN)))).isFalse();
        assertThat(roles.isContainsAny(new Roles())).isFalse();
    }
}
