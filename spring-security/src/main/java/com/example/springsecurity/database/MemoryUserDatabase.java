package com.example.springsecurity.database;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MemoryUserDatabase {
    public static final Map<String, User> userDB = new HashMap<>();

    static {
        // default customer user
        String userIdOfCustomer = "sol";
        String passwordOfCustomer = "$2a$10$9ZVmRiKcPDDO.lCiSBd8TO6AcFekug1WDQjjj1FoU9fllBEwt4FUy";
        List<String> rolesOfCustomer = List.of("customer");

        // default seller user
        String userIdOfSeller = "seller";
        String passwordOfSeller = "$2a$10$6uN6qaVxfUxHJOn917TdwezefDbhFABHbeAtAX2V9jA35OiBzuUOa"; //seller
        List<String> rolesOfSeller = List.of("seller", "customer");

        // default admin user
        String userIdOfAdmin = "admin";
        String passwordOfAdmin = "$2a$12$FTQFZ96f18OuLbt08eNjYuA.HimNhH6CcR.Y8uZpmPuDRQoKEpLti"; // admin
        List<String> rolesOfAdmin = List.of("admin", "seller", "customer");

        userDB.putAll(
                Map.of(
                        userIdOfAdmin, new User(userIdOfAdmin, passwordOfAdmin, rolesOfAdmin, LocalDateTime.MIN, true),
                        userIdOfSeller, new User(userIdOfSeller, passwordOfSeller, rolesOfSeller, LocalDateTime.MIN, true),
                        userIdOfCustomer, new User(userIdOfCustomer, passwordOfCustomer, rolesOfCustomer, LocalDateTime.MIN, true)
                )
        );
    }

    private MemoryUserDatabase() {}
}
