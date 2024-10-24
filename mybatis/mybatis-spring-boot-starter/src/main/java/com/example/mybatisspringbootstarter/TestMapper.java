package com.example.mybatisspringbootstarter;

import com.example.mybatisspringbootstarter.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {

    User findById(@Param("id") long id);
}
