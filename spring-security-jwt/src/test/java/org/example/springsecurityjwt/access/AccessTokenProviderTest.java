package org.example.springsecurityjwt.access;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.BDDAssertions.catchThrowable;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.springsecurityjwt.common.BearerAuthenticationToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

class AccessTokenProviderTest {

    AccessTokenProvider accessTokenProvider = new AccessTokenProvider(new ObjectMapper());

    @Test
    @DisplayName("Subject가 'access token'가 아닌 경우 예외를 발생한다.")
    void checkSupported_fail_when_not_access_token() {
        // given
        Claims mock = Jwts.claims()
                .subject("refresh token")
                .build();

        // when
        Throwable throwable = catchThrowable(() -> accessTokenProvider.checkSupported(mock));

        // then
        assertThat(throwable).isInstanceOf(BadCredentialsException.class)
                .hasMessage("인증 정보를 확인하세요.");
    }

    @Test
    @DisplayName("Subject가 'access token'가 아닌 경우 예외를 발생하지 않는다.")
    void checkSupported_success() {
        // given
        Claims mock = Jwts.claims()
                .subject("access token")
                .build();

        // when
        assertThatCode(() -> accessTokenProvider.checkSupported(mock)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효한 토큰인 경우 인증 상태의 AccessUser(Authentication)이 반환된다.")
    void authenticate() {
        // given
        BearerAuthenticationToken bearerAuthenticationToken = new BearerAuthenticationToken(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhY2Nlc3MgdG9rZW4iLCJ1c2VySW5mbyI6IntcImlkXCI6MixcImVtYWlsXCI6XCJzZWxsZXJAc29sLmNvbVwiLFwibmFtZVwiOlwic2VsbGVyXCIsXCJhdXRob3JpdGllc1wiOlt7XCJhdXRob3JpdHlcIjpcIlNFTExFUlwifV19IiwiaWF0IjoxNzE1NzcwNjY4fQ.hLXIQV1P_dMEE70iLY2LiuxsWTtJr2EB9MbSjhNt8bM",
                false);

        // when
        Authentication authenticate = accessTokenProvider.authenticate(bearerAuthenticationToken);

        // then
        assertThat(authenticate.isAuthenticated()).isTrue();
    }
}
