package org.example.springsecurityjwt.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
public class AuthUserDetails implements UserDetails {

    private Long id;

    private String email;

    private String name;

    private String encryptedPassword;

    private List<SampleAuthority> authorities;

    @Override
    public List<SampleAuthority> getAuthorities() {
        return authorities;
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
