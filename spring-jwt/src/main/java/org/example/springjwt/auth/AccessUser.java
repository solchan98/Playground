package org.example.springjwt.auth;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "email", "userName", "roles"})
public class AccessUser {

    private long userId;

    private String email;

    private String userName;

    private Roles roles;

    public static AccessUser from(User user) {
        return new AccessUser(user.getId(), user.getEmail(), user.getName(), user.getRoles());
    }

    public boolean isAccessible(Roles roles) {
        if (roles.isContainsRole(Role.ALL)) {
            return true;
        }

        return this.roles.isContainsAny(roles);
    }
}
