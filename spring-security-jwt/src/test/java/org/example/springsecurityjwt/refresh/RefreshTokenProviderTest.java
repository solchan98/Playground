package org.example.springsecurityjwt.refresh;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.List;
import org.example.springsecurityjwt.auth.AuthUserDetails;
import org.example.springsecurityjwt.auth.BearerAuthenticationToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

class RefreshTokenProviderTest {

    RefreshTokenRepository refreshTokenRepository = mock();
    UserDetailsService userDetailsService = mock();

    RefreshTokenProvider refreshTokenProvider = new RefreshTokenProvider(refreshTokenRepository, userDetailsService);

    @Test
    @DisplayName("Subject가 'refresh token'가 아닌 경우 예외를 발생한다.")
    void checkSupported_fail_when_not_access_token() {
        // given
        Claims mock = Jwts.claims()
                .subject("access token")
                .build();

        // when
        Throwable throwable = catchThrowable(() -> refreshTokenProvider.checkSupported(mock));

        // then
        assertThat(throwable).isInstanceOf(BadCredentialsException.class)
                .hasMessage("인증 정보를 확인하세요.");
    }

    @Test
    @DisplayName("Subject가 'refresh token'가 아닌 경우 예외를 발생하지 않는다.")
    void checkSupported_success() {
        // given
        Claims mock = Jwts.claims()
                .subject("refresh token")
                .build();

        // when
        assertThatCode(() -> refreshTokenProvider.checkSupported(mock)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효한 토큰인 경우 인증 상태의 AccessUser(Authentication)이 반환된다.")
    void authenticate() {
        // given
        String email = "seller@sol.com";
        BearerAuthenticationToken bearerAuthenticationToken = new BearerAuthenticationToken(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoIHRva2VuIiwiZW1haWwiOiJzZWxsZXJAc29sLmNvbSIsImlhdCI6MTcxNTc3MDk5OX0.EnIYLNpBSBKxkI1Ylps2jZJlXHMUkaCK_q_mMe4lOqY",
                false);
        given(refreshTokenRepository.existsByToken(bearerAuthenticationToken.getName()))
                .willReturn(true);
        AuthUserDetails mockUserDetails = new AuthUserDetails(1L, email, "seller", "a1234567******", List.of());
        given(userDetailsService.loadUserByUsername(email))
                .willReturn(mockUserDetails);

        // when
        Authentication authenticate = refreshTokenProvider.authenticate(bearerAuthenticationToken);

        // then
        assertThat(authenticate.isAuthenticated()).isTrue();
    }
}
