package com.example.mybatisspringbootstarter.entity;

public class User {

    private final Long id;
    private final String username;
    private final String countryCode;

    public User(Long id, String username, String countryCode) {
        this.id = id;
        this.username = username;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
