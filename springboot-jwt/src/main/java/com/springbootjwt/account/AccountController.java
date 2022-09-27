package com.springbootjwt.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<AccountResponse> signUp(
            @RequestBody SignUpRequest signUpRequest
    ) {
        AccountResponse accountResponse = accountService.signUp(signUpRequest);
        return ResponseEntity.ok(accountResponse);
    }
}
