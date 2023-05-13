package com.example.springsecurity.persistence.impl;

import com.example.springsecurity.database.User;
import com.example.springsecurity.persistence.UserRepository;

import java.util.Collection;
import java.util.Optional;

import static com.example.springsecurity.database.MemoryUserDatabase.userDB;

public class MemoryUserRepositoryImpl implements UserRepository {

    @Override
    public void create(User user) {
        userDB.put(user.getUsername(), user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        boolean contains = userDB.containsKey(username);

        if (contains) {
            return Optional.of(userDB.get(username));
        }

        return Optional.empty();
    }

    @Override
    public Collection<User> findAll() {
        return userDB.values();
    }
}
