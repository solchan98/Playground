package com.example.mybatisspringbootstarter;

import com.example.mybatisspringbootstarter.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestMapperTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    void test() {
        User user = testMapper.findById(1);
        System.out.println(user);
    }
}
