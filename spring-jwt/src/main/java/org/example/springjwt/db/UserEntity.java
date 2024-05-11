package org.example.springjwt.db;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springjwt.auth.Role;
import org.example.springjwt.auth.Roles;
import org.example.springjwt.auth.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private Long id;

    private String email;

    private String name;

    private String encryptedPassword;

    private List<String> roles;

    public User toUser() {
        return new User(id, email, name, encryptedPassword, Roles.from(roles));
    }

    public static UserEntity from(User user) {
        List<String> stringRoles = user.getRoles()
                .getValues()
                .stream()
                .map(Role::name)
                .toList();

        return new UserEntity(user.getId(), user.getEmail(), user.getName(), user.getEncryptedPassword(), stringRoles);
    }
}
