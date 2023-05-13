package com.example.springsecurity.database;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private final String username; // not user id, key of identifiable
    private final String userId;
    private final String password;
    private final List<String> roles;
    private final LocalDateTime blockedAt;
    private final Boolean enabled;

    public User(String username, String userId, String password, List<String> roles, LocalDateTime blockedAt, Boolean enabled) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.roles = roles;
        this.blockedAt = blockedAt;
        this.enabled = enabled;
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