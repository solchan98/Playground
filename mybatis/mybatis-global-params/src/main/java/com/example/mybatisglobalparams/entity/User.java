package com.example.mybatisglobalparams.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(countryCode, user.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, countryCode);
    }
}
