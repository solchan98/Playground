package org.example.springjwt.auth;

public interface Authentication {

    Roles roles();

    Object getPrincipal();

    Object getCredentials();
}
