package com.example.springsecurity.persistence;

import com.example.springsecurity.database.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    void create(User user);
    Optional<User> findByUsername(String username);
    Collection<User> findAll();
}
