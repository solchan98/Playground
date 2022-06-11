package com.example.springbootjwt.domain.user.service;

import com.example.springbootjwt.domain.user.domain.Account;
import com.example.springbootjwt.domain.user.domain.AccountRepository;
import com.example.springbootjwt.domain.user.domain.AuthAccount;
import com.example.springbootjwt.global.common.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomAccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("토큰을 확인하세요."));
        return new AuthAccount(account);
    }
}
