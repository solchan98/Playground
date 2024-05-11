package org.example.springjwt.auth;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "email", "name", "encryptedPassword", "roles"})
public class User {

    private long id;

    private String email;

    private String name;

    private String encryptedPassword;

    private Roles roles;
}
