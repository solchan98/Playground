package com.example.springjwt.domain.account.service;

import com.example.springjwt.domain.account.domain.Account;
import com.example.springjwt.domain.account.domain.dao.AccountRepository;
import com.example.springjwt.domain.account.domain.dto.SignUp;
import com.example.springjwt.domain.account.domain.dto.request.LoginRequest;
import com.example.springjwt.domain.account.domain.dto.response.LoginResponse;
import com.example.springjwt.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final RedisTemplate<String, String> redisTemplate;

    private final JwtTokenProvider jwtTokenProvider;

    public SignUp signUp(SignUp signUp) {
        boolean exists = accountRepository.existsByEmail(signUp.getEmail());
        if(exists) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(signUp.getPassword());
        Account account = Account.builder().email(signUp.getEmail()).password(encodedPassword).build();
        Account savedAccount = accountRepository.save(account);
        return SignUp.builder().email(savedAccount.getEmail()).password(savedAccount.getPassword()).build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Account account = accountRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("이메일 혹은 비밀번호를 확인하세요"));
        checkPassword(loginRequest, account);
        String accessToken = jwtTokenProvider.createAccessToken(loginRequest.getEmail(), List.of("ROLE_USER"));
        String refreshToken = jwtTokenProvider.createRefreshToken(loginRequest.getEmail(), List.of("ROLE_USER"));
        this.setRefreshTokenInRedis(account, refreshToken);
        return LoginResponse.of(loginRequest.getEmail(), accessToken, refreshToken);
    }

    private void checkPassword(LoginRequest loginRequest, Account account) {
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), account.getPassword());
        if(!matches) {
            throw new RuntimeException("이메일 혹은 비밀번호를 확인하세요.");
        }
    }

    private void setRefreshTokenInRedis(Account account, String refreshToken) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        long expiration = jwtTokenProvider.getExpiration(refreshToken).getTime() - System.currentTimeMillis();
        values.set(account.getEmail(), refreshToken, Duration.ofMillis(expiration));
    }
}
