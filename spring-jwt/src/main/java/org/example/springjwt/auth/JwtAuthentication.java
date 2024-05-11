package org.example.springjwt.auth;

public class JwtAuthentication implements Authentication {

    private String token;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Roles roles() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return getPrincipal();
    }
}
