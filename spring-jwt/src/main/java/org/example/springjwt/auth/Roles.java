package org.example.springjwt.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"values"})
public class Roles {

    private Set<Role> values = new HashSet<>();

    public static Roles from(List<String> stringRoles) {
        return new Roles(stringRoles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet()));
    }

    public static Roles from(Role[] arrayRoles) {
        return new Roles(Set.of(arrayRoles));
    }

    public boolean isContainsRole(Role role) {
        return values.contains(role);
    }

    public boolean isContainsAny(Roles roles) {
        return CollectionUtils.containsAny(values, roles.values);
    }
}
