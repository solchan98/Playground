package org.example.springsecurityjwt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;

public abstract class TokenProvider implements AuthenticationProvider {

    private SecretKey key = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode("secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey"));

    public BearerAuthenticationToken createToken(String subject, Map<String, Object> payload, long ttl) {
        Claims claims = Jwts.claims()
                .subject(subject)
                .add(payload)
                .build();
        Date date = new Date();

        String token = Jwts.builder()
                .claims(claims)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + ttl))
                .signWith(key)
                .compact();

        return new BearerAuthenticationToken(token, true);
    }

    public Claims verify(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new BadCredentialsException("인증 정보를 확인하세요.", e);
        }
    }

    public abstract void checkTokenType(Claims claims);
}
