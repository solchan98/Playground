package com.example.springsecurity.database;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MemoryUserDatabase {
    public static final Map<String, User> userDB = new HashMap<>();

    static {
        String username = "a3601804-496f-456f-8bc8-4d1d7511d7d1";
        String userId = "sol";
        String password = "$2a$10$9ZVmRiKcPDDO.lCiSBd8TO6AcFekug1WDQjjj1FoU9fllBEwt4FUy";
        List<String> roles = List.of("admin");

        userDB.put(username, new User(username, userId, password, roles, LocalDateTime.MIN, true));
    }

    private MemoryUserDatabase() {}
}
