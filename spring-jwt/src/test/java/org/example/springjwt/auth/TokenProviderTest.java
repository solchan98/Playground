package org.example.springjwt.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.example.springjwt.auth.AccessUser;
import org.example.springjwt.auth.Role;
import org.example.springjwt.auth.Roles;
import org.example.springjwt.auth.TokenProvider;
import org.example.springjwt.presentation.exception.AuthenticationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    TokenProvider tokenProvider = new TokenProvider(new ObjectMapper());

    @Test
    @DisplayName("토큰이 유효하면 AccessUser를 반환한다.")
    void verify() {
        // given
        String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhY2Nlc3MgdG9rZW4iLCJ1c2VySW5mbyI6IntcInVzZXJJZFwiOjEsXCJlbWFpbFwiOlwic29sQHNvbC5jb21cIixcInVzZXJOYW1lXCI6XCJzb2xcIixcInJvbGVzXCI6e1widmFsdWVzXCI6W1wiQURNSU5cIl19fSIsImlhdCI6MTcxNTQxNjQ3N30.d3UY2OJiZojotmtE83PLhn2Gn7YnG76wbxd9cmr7eHk";
        AccessUser expected = new AccessUser(1L, "sol@sol.com", "sol", new Roles(Collections.singleton(Role.ADMIN)));

        AccessUser result = tokenProvider.verify(validToken);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("잘못된 JWT 형식의 토큰은 401 예외가 발생한다.")
    void verify_fail_when_invalid_format() {

        Throwable throwable = catchThrowable(() -> tokenProvider.verify("invalid token"));

        assertThat(throwable).isInstanceOf(AuthenticationException.class)
                .hasMessage("인증 정보를 확인하세요.");
    }
}
