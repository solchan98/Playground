package org.example.springsecurityjwt.access;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication implements Authentication {

    private final String token;

    private static final boolean AUTHENTICATE = false;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return getCredentials();
    }

    @Override
    public boolean isAuthenticated() {
        return AUTHENTICATE;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return null;
    }
}
