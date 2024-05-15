package org.example.springsecurityjwt.access;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AccessAuthenticationToken implements Authentication {

    private final String token;

    private static final boolean AUTHENTICATE = false;

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
        return AUTHENTICATE;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // do nothing
    }

    @Override
    public String getName() {
        return (String) getCredentials();
    }
}
