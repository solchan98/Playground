package com.example.springsecurity.persistence;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class UserVO implements UserDetails {
    private final String username; // not user id, key of identifiable
    private final String userId;
    private final String password;
    private final List<SimpleGrantedAuthority> roles;
    private final Boolean nonLocked;
    private final Boolean enabled;

    public UserVO(String username, String userId, String password, List<String> roles, LocalDateTime blockedAt, Boolean enabled) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.roles = roles.stream().map(SimpleGrantedAuthority::new).toList();
        this.nonLocked = LocalDateTime.now().minusMinutes(5).isAfter(blockedAt);
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // account is always active
    }

    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // account is always state of credential
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
