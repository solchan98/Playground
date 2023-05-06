package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.repository.BoardMapper;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis-config.xml";

        try(
                InputStream inputStream = Resources.getResourceAsStream(resource);
                SqlSession sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession()
        ) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            Board sol = mapper.selectBoard("sol");
            System.out.println(sol.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}