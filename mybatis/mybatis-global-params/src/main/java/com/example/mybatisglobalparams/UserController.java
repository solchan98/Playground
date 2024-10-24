package com.example.mybatisglobalparams;

import com.example.mybatisglobalparams.entity.User;
import com.example.mybatisglobalparams.mappers.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("{id}")
    @Transactional(readOnly = true)
    public User getById(@PathVariable("id") Long id) {
        return userMapper.findById(id);
    }

    @GetMapping("")
    @Transactional(readOnly = true)
    public User getByIdAndUserName(
            @RequestParam("id") long id,
            @RequestParam("username") String username
    ) {
        return userMapper.findByIdAndUserName(id, username);
    }
}
