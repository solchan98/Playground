package com.example.springjwt.domain.account.service;

import com.example.springjwt.domain.account.domain.Account;
import com.example.springjwt.domain.account.domain.dao.AccountRepository;
import com.example.springjwt.domain.account.domain.dto.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

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
}
