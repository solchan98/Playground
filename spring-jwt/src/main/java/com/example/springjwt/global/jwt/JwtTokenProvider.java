package com.example.springjwt.global.jwt;

import com.example.springjwt.domain.account.service.CustomUserDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.key}")
    private String secretKey;

    private long accessTokenValidTime = 60 * 60 * 1000L; // 1h
    private long refreshTokenValidTime = 3 * 24 * 60 * 60 * 1000L; // 3day

    private final CustomUserDetailService customUserDetailService;

    // 의존성 주입 후, 초기화를 수행
    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String email, List<String> roles){
        return this.createToken(email, roles, accessTokenValidTime);
    }

    public String createRefreshToken(String email, List<String> roles) {
        return this.createToken(email, roles, refreshTokenValidTime);
    }

    public String createToken(String email, List<String> roles, long tokenValid) {
        Claims claims = Jwts.claims().setSubject(email); // claims 생성 및 payload 설정
        claims.put("roles", roles); // 권한 설정, key/ value 쌍으로 저장

        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims) // 발행 유저 정보 저장
                .setIssuedAt(date) // 발행 시간 저장
                .setExpiration(new Date(date.getTime() + tokenValid)) // 토큰 유효 시간 저장
                .signWith(SignatureAlgorithm.HS256, secretKey) // 해싱 알고리즘 및 키 설정
                .compact(); // 생성
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Date getExpiration(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
    }

    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("Authorization") != null )
            return request.getHeader("Authorization").substring(7);
        return null;
    }

    public boolean validateToken(HttpServletRequest request, String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", "토큰이 만료되었습니다.");
            return false;
        } catch (MalformedJwtException e) {
            request.setAttribute("exception", "토큰의 형식이 잘못되었습니다.");
            return false;
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", "토큰이 비어있습니다.");
            return false;
        }
    }
}
