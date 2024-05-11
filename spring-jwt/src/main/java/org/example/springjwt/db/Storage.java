package org.example.springjwt.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.springjwt.auth.Role;

public final class Storage {

    public static final Map<Long, UserEntity> users = new HashMap<>();

    public static final Map<String, Long> userIdByEmail = new HashMap<>();

    public static final Map<Long, Set<RoleEntity>> userRoles = new HashMap<>();

    static {
        initUser(new UserEntity(1L, "admin@sol.com", "admin", "a1234567******", List.of(String.valueOf(Role.ADMIN))));
        initUser(new UserEntity(2L, "seller@sol.com", "seller", "a1234567******", List.of(String.valueOf(Role.SELLER))));
    }

    static void initUser(UserEntity userEntity) {
        users.put(userEntity.getId(), userEntity);
        userIdByEmail.put(userEntity.getEmail(), userEntity.getId());

        Set<RoleEntity> roleEntitySet = userEntity.getRoles()
                .stream()
                .map((role -> new RoleEntity(userEntity.getId(), role)))
                .collect(Collectors.toSet());
        userRoles.put(userEntity.getId(), roleEntitySet);
    }

    private Storage() {
    }

    public static void clearAll() {
        users.clear();
        userRoles.clear();
    }
}
