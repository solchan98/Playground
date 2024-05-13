package org.example.springsecurityjwt.db;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springsecurityjwt.common.AuthUser;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private Long id;

    private String email;

    private String name;

    private String encryptedPassword;

    private List<String> roles;

    public AuthUser toUser() {
        return new AuthUser(id, email, name, encryptedPassword);
    }
}
