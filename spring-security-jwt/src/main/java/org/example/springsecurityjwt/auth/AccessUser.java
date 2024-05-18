package org.example.springsecurityjwt.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessUser implements Authentication {

    private long id;

    private String email;

    private String name;

    private List<SampleAuthority> authorities;

    private boolean authenticated;

    public static AccessUser authenticated(AuthUserDetails userDetails) {
        return new AccessUser(userDetails.getId(), userDetails.getEmail(), userDetails.getName(),
                userDetails.getAuthorities(), true);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public Object getCredentials() {
        return email;
    }

    @Override
    @JsonIgnore
    public Object getDetails() {
        return getCredentials();
    }

    @Override
    @JsonIgnore
    public Object getPrincipal() {
        return getCredentials();
    }

    @Override
    @JsonIgnore
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    @JsonIgnore
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }
}
