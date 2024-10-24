package com.example.mybatisglobalparams.mappers;

import com.example.mybatisglobalparams.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findById(@Param("id") long id);

    User findByIdAndUserName(
            @Param("id") long id,
            @Param("username") String username
    );
}
