package com.springbootjwt.account;

import com.springbootjwt.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AccountResponse signUp(SignUpRequest signUpRequest) {
        boolean isExist = accountRepository
                .existsByEmail(signUpRequest.getEmail());
        if (isExist) throw new BadRequestException("이미 존재하는 이메일입니다.");
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Account account = new Account(
                signUpRequest.getEmail(),
                encodedPassword,
                signUpRequest.getNickname());

        account = accountRepository.save(account);
        return AccountResponse.of(account);
    }

    @Transactional(readOnly = true)
    public AccountResponse login(LoginRequest loginRequest) {
        Account account = accountRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("아이디 혹은 비밀번호를 확인하세요."));

        boolean matches = passwordEncoder.matches(
                loginRequest.getPassword(),
                account.getPassword());
        if (!matches) throw new BadRequestException("아이디 혹은 비밀번호를 확인하세요.");

        return AccountResponse.of(account);
    }
}
