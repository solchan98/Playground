package org.example.springjwt.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.example.springjwt.auth.Role.ADMIN;
import static org.example.springjwt.auth.Role.READ_ONLY;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthorizationManagerTest {

    AuthorizationManager authorizationManager = new AuthorizationManager();

    @Test
    @DisplayName("접근 권한이 없는 경우 403 예외를 던진다.")
    void verify() {
        // given
        AccessUser accessUser = new AccessUser(1L, "sol@sol.com", "sol", new Roles());
        Roles requiredAnyRoles = new Roles(Set.of(ADMIN, READ_ONLY));

        // when
        Throwable throwable = catchThrowable(() -> authorizationManager.verify(accessUser, requiredAnyRoles));

        // then
        assertThat(throwable).isInstanceOf(AuthorizationManager.class)
                .hasMessage("권한을 확인하세요.");
    }

}
