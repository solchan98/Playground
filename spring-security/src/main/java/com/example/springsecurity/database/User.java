package com.example.springsecurity.database;

import java.util.List;

public class User {
    private final String username; // not user id, key of identifiable
    private final String userId;
    private final String password;
    private final Boolean enabled;
    private final List<String> roles;

    public User(String username, String userId, String password, Boolean enabled, List<String> roles) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public List<String> getRoles() {
        return roles;
    }
}
