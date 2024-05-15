package org.example.springsecurityjwt.refresh;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
public class RefreshAuthenticationToken implements Authentication {

    private String email;

    private String token;

    private boolean authenticated = false;

    public RefreshAuthenticationToken(String email, String token) {
        this(email, token, false);
    }

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
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return (String) getCredentials();
    }

    public String getEmail() {
        return (String) getPrincipal();
    }
}
