package org.example.springjwt.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.springjwt.auth.Role.ADMIN;
import static org.example.springjwt.auth.Role.ALL;
import static org.example.springjwt.auth.Role.READ_ONLY;

import java.util.Collections;
import org.example.springjwt.auth.AccessUser;
import org.example.springjwt.auth.Roles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccessUserTest {

    @Test
    @DisplayName("권한으로 접근 가능한지 확인할 수 있다.")
    void isAccessible() {
        // given
        Roles accessibleRoles = new Roles(Collections.singleton(ADMIN));
        AccessUser readOnlyUser = new AccessUser(1L, "sol@sol.com" ,"read only", new Roles(Collections.singleton(READ_ONLY)));
        AccessUser adminUser = new AccessUser(1L, "sol@sol.com" , "admin", new Roles(Collections.singleton(ADMIN)));

        // when then
        assertThat(readOnlyUser.isAccessible(accessibleRoles)).isFalse();
        assertThat(adminUser.isAccessible(accessibleRoles)).isTrue();
    }

    @Test
    @DisplayName("API 권한이 ALL인 경우, 권한 상관없이 접근 가능하다.")
    void isAccessible_when_api_role_is_all() {
        // given
        Roles accessibleRoles = new Roles(Collections.singleton(ALL));
        AccessUser emptyRoleUser = new AccessUser(1L, "sol@sol.com" ,"read only", new Roles());

        // when then
        assertThat(emptyRoleUser.isAccessible(accessibleRoles)).isTrue();
    }
}
