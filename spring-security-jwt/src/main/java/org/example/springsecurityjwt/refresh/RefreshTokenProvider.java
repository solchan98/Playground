
package org.example.springsecurityjwt.refresh;

import io.jsonwebtoken.Claims;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.auth.AccessUser;
import org.example.springsecurityjwt.auth.AuthUserDetails;
import org.example.springsecurityjwt.auth.BearerAuthenticationToken;
import org.example.springsecurityjwt.auth.TokenProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component(value = "refreshTokenProvider")
@RequiredArgsConstructor
public class RefreshTokenProvider extends TokenProvider {

    private static final String TOKEN_SUBJECT = "refresh token";

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims claims = super.verify(authentication.getName());
        checkTokenType(claims);

        String email = claims.get("email", String.class);
        AuthUserDetails authUserDetails = (AuthUserDetails) userDetailsService.loadUserByUsername(email);

        if (!refreshTokenRepository.existsByToken(authentication.getName())) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }

        return AccessUser.authenticated(authUserDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (BearerAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public BearerAuthenticationToken createToken(String email) {
        long tokenLive = 1000L * 60L * 60L; // 1h
        BearerAuthenticationToken token = super.createToken(TOKEN_SUBJECT, Map.of("email", email), tokenLive);
        refreshTokenRepository.save(email, token.getName(), tokenLive);

        return token;
    }

    @Override
    public void checkTokenType(Claims claims) {
        if (!TOKEN_SUBJECT.equals(claims.getSubject())) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }
    }
}
