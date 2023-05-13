package com.example.springsecurity.database;

import java.util.HashMap;
import java.util.Map;

public final class MemoryUserDatabase {
    public static final Map<String, User> userDB = new HashMap<>();

    private MemoryUserDatabase() {}
}
