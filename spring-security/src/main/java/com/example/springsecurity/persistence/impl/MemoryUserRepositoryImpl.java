package com.example.springsecurity.persistence.impl;

import com.example.springsecurity.database.User;
import com.example.springsecurity.persistence.UserRepository;
import com.example.springsecurity.persistence.UserVO;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static com.example.springsecurity.database.MemoryUserDatabase.userDB;

public class MemoryUserRepositoryImpl implements UserRepository {

    @Override
    public void create(UserVO userVO) {
        userDB.put(userVO.getUsername(), toUser(userVO));
    }

    @Override
    public Optional<UserVO> findByUsername(String username) {
        boolean contains = userDB.containsKey(username);

        if (contains) {
            return Optional.of(toUserVO(userDB.get(username)));
        }

        return Optional.empty();
    }

    @Override
    public Collection<UserVO> findAll() {
        return userDB.values().stream().map(this::toUserVO).toList();
    }

    private User toUser(UserVO userVO) {
        return new User(
                userVO.getUsername(),
                userVO.getUserId(),
                userVO.getPassword(),
                userVO.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                LocalDateTime.MIN, // set '-999999999-01-01T00:00:00'
                userVO.isEnabled()
        );
    }

    private UserVO toUserVO(User user) {
        return new UserVO(
                user.getUsername(),
                user.getUserId(),
                user.getPassword(),
                user.getRoles(),
                user.getBlockedAt(),
                user.getEnabled()
        );
    }
}
