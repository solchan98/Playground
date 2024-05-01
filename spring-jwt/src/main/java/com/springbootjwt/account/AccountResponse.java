package com.springbootjwt.account;

import lombok.Getter;

@Getter
public class AccountResponse {

    private final Long accountId;

    private final String email;

    private final String nickname;

    private AccountResponse(Long accountId, String email, String nickname) {
        this.accountId = accountId;
        this.email = email;
        this.nickname = nickname;
    }

    public static AccountResponse of(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getEmail(),
                account.getNickname());
    }
}
