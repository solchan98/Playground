package org.example.springsecurityjwt.auth;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class BearerAuthenticationToken implements Authentication {

    private final String token;

    private boolean authenticate = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
        return authenticate;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticate = isAuthenticated;
    }

    @Override
    public String getName() {
        return (String) getCredentials();
    }
}
