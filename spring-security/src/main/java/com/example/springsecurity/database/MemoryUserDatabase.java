package com.example.springsecurity.database;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MemoryUserDatabase {
    public static final Map<String, User> userDB = new HashMap<>();

    static {
        String userId = "sol";
        String password = "$2a$10$9ZVmRiKcPDDO.lCiSBd8TO6AcFekug1WDQjjj1FoU9fllBEwt4FUy";
        List<String> roles = List.of("admin");

        userDB.put(userId, new User(userId, password, roles, LocalDateTime.MIN, true));
    }

    private MemoryUserDatabase() {}
}
