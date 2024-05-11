package org.example.springjwt.auth;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailPasswordAuthentication implements Authentication {

    private String email;

    private String plainPassword;

    @Override
    public Roles roles() {
        return new Roles();
    }

    @Override
    public String getPrincipal() {
        return email;
    }

    @Override
    public String getCredentials() {
        return plainPassword;
    }
}
