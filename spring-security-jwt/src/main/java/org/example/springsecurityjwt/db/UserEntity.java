package org.example.springsecurityjwt.db;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springsecurityjwt.common.AccessUser;
import org.example.springsecurityjwt.common.SampleAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

    private Long id;

    private String email;

    private String name;

    private String encryptedPassword;

    private List<String> roles;

    public AccessUser toAccessUser() {
        List<SampleAuthority> list = roles.stream()
                .map(SampleAuthority::new)
                .toList();
        return new AccessUser(id, email, name, list, false);
    }

    @Override
    public List<SampleAuthority> getAuthorities() {
        return roles.stream()
                .map(SampleAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
