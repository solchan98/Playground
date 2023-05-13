package com.example.springsecurity.persistence;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserVO implements UserDetails {
    private final String username; // not user id, key of identifiable
    private final String userId;
    private final String password;
    private final List<SimpleGrantedAuthority> roles;
    private final Boolean enabled;

    public UserVO(String username, String userId, String password, List<String> roles, Boolean enabled) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.roles = roles.stream().map(SimpleGrantedAuthority::new).toList();
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
