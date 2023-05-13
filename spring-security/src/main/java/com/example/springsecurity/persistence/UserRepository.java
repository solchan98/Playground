package com.example.springsecurity.persistence;

import com.example.springsecurity.database.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    Collection<User> findAll();
}
