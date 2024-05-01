package com.example.springbootjwt.domain.user.service;

import com.example.springbootjwt.domain.user.domain.Account;
import com.example.springbootjwt.domain.user.domain.AccountRepository;
import com.example.springbootjwt.domain.user.domain.dto.LoginResponseDto;
import com.example.springbootjwt.global.common.exception.BadRequestException;
import com.example.springbootjwt.global.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void logout(String email, String accessToken) {
        jwtProvider.logout(email, accessToken);
    }

    public LoginResponseDto reIssueAccessToken(String email, String refreshToken) {
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("존재하지 않는 유저입니다."));
        jwtProvider.checkRefreshToken(email, refreshToken);
        String accessToken = jwtProvider.createAccessToken(account.getEmail(), account.getRole());
        return new LoginResponseDto(accessToken, refreshToken);
    }

    public LoginResponseDto login(String email, String password) {
        Account account = accountRepository
                .findByEmail(email).orElseThrow(() -> new BadRequestException("아이디 혹은 비밀번호를 확인하세요."));
        checkPassword(password, account.getPassword());
        String accessToken = jwtProvider.createAccessToken(account.getEmail(), account.getRole());
        String refreshToken = jwtProvider.createRefreshToken(account.getEmail(), account.getRole());
        return new LoginResponseDto(accessToken, refreshToken);
    }

    private void checkPassword(String password, String encodedPassword) {
        boolean isSame = passwordEncoder.matches(password, encodedPassword);
        if(!isSame) {
            throw new BadRequestException("아이디 혹은 비밀번호를 확인하세요.");
        }
    }
    @Transactional
    public void signUp(String email, String nickname, String password) {
        checkEmailIsDuplicate(email);
        checkPasswordConvention(password);
        String encodedPassword = passwordEncoder.encode(password);
        Account newAccount = Account.of(email, nickname, encodedPassword);
        accountRepository.save(newAccount);
    }

    private void checkEmailIsDuplicate(String email) {
        boolean isDuplicate = accountRepository.existsByEmail(email);
        if(isDuplicate) {
            throw new BadRequestException("이미 존재하는 회원입니다.");
        }
    }

    private void checkPasswordConvention(String password) {
        // TODO: Check Password Convention
    }
}
