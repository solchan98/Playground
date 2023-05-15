package com.example.springsecurity.database;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private final String userId;
    private final String password;
    private final List<String> roles;
    private final LocalDateTime blockedAt;
    private final Boolean enabled;

    public User(String userId, String password, List<String> roles, LocalDateTime blockedAt, Boolean enabled) {
        this.userId = userId;
        this.password = password;
        this.roles = roles;
        this.blockedAt = blockedAt;
        this.enabled = enabled;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
