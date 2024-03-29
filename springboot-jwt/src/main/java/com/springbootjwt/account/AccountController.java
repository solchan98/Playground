package com.springbootjwt.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootjwt.jwt.AccountDetails;
import com.springbootjwt.jwt.JwtProvider;
import com.springbootjwt.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JwtProvider jwtProvider;

    @PostMapping("/sign-up")
    public AccountResponse signUp(
            @RequestBody SignUpRequest signUpRequest
    ) {
        return accountService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(
            @RequestBody LoginRequest loginRequest
    ) throws JsonProcessingException {
        AccountResponse accountResponse = accountService.login(loginRequest);
        return jwtProvider.createTokensByLogin(accountResponse);
    }

    @GetMapping("/test")
    public String test() {
        return "good!";
    }

    @GetMapping("/reissue")
    public TokenResponse reissue(
            @AuthenticationPrincipal AccountDetails accountDetails
    ) throws JsonProcessingException {
        AccountResponse accountResponse = AccountResponse.of(accountDetails.getAccount());
        return jwtProvider.reissueAtk(accountResponse);
    }
}