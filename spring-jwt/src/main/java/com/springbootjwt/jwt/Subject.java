package com.springbootjwt.jwt;

import lombok.Getter;

@Getter
public class Subject {

    private final Long accountId;

    private final String email;

    private final String nickname;

    private final String type;

    private Subject(Long accountId, String email, String nickname, String type) {
        this.accountId = accountId;
        this.email = email;
        this.nickname = nickname;
        this.type = type;
    }

    public static Subject atk(Long accountId, String email, String nickname) {
        return new Subject(accountId, email, nickname, "ATK");
    }

    public static Subject rtk(Long accountId, String email, String nickname) {
        return new Subject(accountId, email, nickname, "RTK");
    }
}
