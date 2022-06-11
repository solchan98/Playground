package com.example.springbootjwt.domain.user.controller;

import com.example.springbootjwt.domain.user.domain.dto.LoginRequestDto;
import com.example.springbootjwt.domain.user.domain.dto.LoginResponseDto;
import com.example.springbootjwt.domain.user.domain.dto.SignUpRequestDto;
import com.example.springbootjwt.domain.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpUser) {
        accountService.signUp(signUpUser.getEmail(), signUpUser.getEmail(), signUpUser.getPassword());
        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto) {
        LoginResponseDto responseDto = accountService.login(loginDto.getEmail(), loginDto.getPassword());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
