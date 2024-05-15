package org.example.springsecurityjwt.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.AuthenticationManager;

public abstract class TokenProvider implements AuthenticationManager {

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
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public abstract void checkSupported(Claims claims);
}
