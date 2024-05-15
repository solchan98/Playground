package org.example.springsecurityjwt.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.springsecurityjwt.common.Role;

public final class Storage {

    public static final Map<Long, UserEntity> users = new HashMap<>();

    public static final Map<String, Long> userIdByEmail = new HashMap<>();

    static {
        initUser(new UserEntity(1L, "admin@sol.com", "admin", "a1234567******", List.of(String.valueOf(Role.ADMIN))));
        initUser(
                new UserEntity(2L, "seller@sol.com", "seller", "a1234567******", List.of(String.valueOf(Role.SELLER))));
    }

    static void initUser(UserEntity userEntity) {
        users.put(userEntity.getId(), userEntity);
        userIdByEmail.put(userEntity.getEmail(), userEntity.getId());
    }

    private Storage() {
    }

    public static void clearAll() {
        users.clear();
    }
}
