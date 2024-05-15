
package org.example.springsecurityjwt;

import io.jsonwebtoken.Claims;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.refresh.RefreshAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component(value = "refreshTokenProvider")
@RequiredArgsConstructor
public class RefreshTokenProvider extends TokenProvider {

    private static final String TOKEN_SUBJECT = "refresh token";

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        checkSupported(authentication);
        RefreshAuthenticationToken refreshToken = (RefreshAuthenticationToken) authentication;
        if (refreshTokenRepository.existsByToken(refreshToken)) {
            refreshToken.setAuthenticated(true);
        }

        return authentication;
    }

    @Override
    public Claims verify(String token) {
        Claims claims = super.verify(token);
        if (!TOKEN_SUBJECT.equals(claims.getSubject())) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }

        return claims;
    }

    public RefreshAuthenticationToken createToken(String email) {
        long tokenLive = 1000L * 60L * 60L; // 1h
        String token = super.createToken(TOKEN_SUBJECT, Map.of("email", email), tokenLive);
        RefreshAuthenticationToken refreshAuthenticationToken = new RefreshAuthenticationToken(email, token);
        refreshTokenRepository.save(refreshAuthenticationToken, tokenLive);

        return refreshAuthenticationToken;
    }

    @Override
    public void checkSupported(Authentication authentication) {
        boolean supported = authentication instanceof RefreshAuthenticationToken;
        if (!supported) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }
    }
}
