package com.example.springjwt.domain.account.controller;

import com.example.springjwt.domain.account.domain.dto.SignUp;
import com.example.springjwt.domain.account.domain.dto.request.LoginRequest;
import com.example.springjwt.domain.account.domain.dto.response.LoginResponse;
import com.example.springjwt.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public SignUp signUp(@RequestBody SignUp signUp) {
        return accountService.signUp(signUp);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest);
    }
}
