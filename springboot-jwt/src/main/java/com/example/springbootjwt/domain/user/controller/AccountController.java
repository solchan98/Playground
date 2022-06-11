package com.example.springbootjwt.domain.user.controller;

import com.example.springbootjwt.domain.user.domain.Account;
import com.example.springbootjwt.domain.user.domain.AuthUser;
import com.example.springbootjwt.domain.user.domain.dto.LoginRequestDto;
import com.example.springbootjwt.domain.user.domain.dto.LoginResponseDto;
import com.example.springbootjwt.domain.user.domain.dto.SignUpRequestDto;
import com.example.springbootjwt.domain.user.service.AccountService;
import com.example.springbootjwt.global.common.dto.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/logout")
    public ResponseEntity<BasicResponse> logout(@AuthUser Account account, HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").substring(7);
        accountService.logout(account.getEmail(), accessToken);
        BasicResponse response = new BasicResponse("로그아웃 완료", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/re-issue")
    public ResponseEntity<LoginResponseDto> reIssue(@RequestParam("email") String email, @RequestParam("refreshToken") String refreshToken) {
        LoginResponseDto responseDto = accountService.reIssueAccessToken(email, refreshToken);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpRequestDto signUpUser) {
        accountService.signUp(signUpUser.getEmail(), signUpUser.getEmail(), signUpUser.getPassword());
        BasicResponse response = new BasicResponse("회원가입 성공", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto) {
        LoginResponseDto responseDto = accountService.login(loginDto.getEmail(), loginDto.getPassword());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
