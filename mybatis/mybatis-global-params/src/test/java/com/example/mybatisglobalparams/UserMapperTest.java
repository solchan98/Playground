package com.example.mybatisglobalparams;

import com.example.mybatisglobalparams.entity.User;
import com.example.mybatisglobalparams.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Import(MyBatisConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void findByIdWithCountryCode() {
        User krSol = new User(null, "KR - sol", "KR");
        User usSol = new User(null, "US - sol", "US");
        userMapper.save(krSol);
        userMapper.save(usSol);

        CountryCodeContext.setCountryCode("KR");
        assertThat(userMapper.findById(krSol.getId())).isEqualTo(krSol);

        CountryCodeContext.setCountryCode("US");
        assertThat(userMapper.findById(usSol.getId())).isEqualTo(usSol);

        CountryCodeContext.setCountryCode("IT");
        assertThat(userMapper.findById(krSol.getId())).isNull();
    }
}
