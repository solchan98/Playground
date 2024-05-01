package org.example.config.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSessionManager {
    private final String resource = "mybatis/mybatis-config.xml";

    public SqlSession getSession() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SqlSessionFactoryBuilder().build(inputStream).openSession(true);
    }

    public void closeSession(SqlSession session) {
        session.close();
    }
}
