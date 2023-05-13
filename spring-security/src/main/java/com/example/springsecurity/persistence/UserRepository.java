package com.example.springsecurity.persistence;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    void create(UserVO user);
    Optional<UserVO> findByUsername(String username);
    Collection<UserVO> findAll();
}
