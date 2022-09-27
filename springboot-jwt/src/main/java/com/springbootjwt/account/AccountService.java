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
}
