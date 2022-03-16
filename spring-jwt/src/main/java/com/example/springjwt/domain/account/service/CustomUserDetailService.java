package com.example.springjwt.domain.account.service;

import com.example.springjwt.domain.account.domain.Account;
import com.example.springjwt.domain.account.domain.UserAccount;
import com.example.springjwt.domain.account.domain.dao.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저 정보를 찾을 수 없습니다."));
        return new UserAccount(account);
    }
}