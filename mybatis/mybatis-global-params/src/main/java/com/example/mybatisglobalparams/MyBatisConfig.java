package com.example.mybatisglobalparams;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // Mapper XML 파일 위치 설정
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        // Type Aliases 패키지 설정
        sessionFactory.setTypeAliasesPackage("com.example.mybatisglobalparams.entity");

        // MyBatis 설정
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);

        sessionFactory.setConfiguration(configuration);

        // 인터셉터 추가
        Interceptor[] plugins = {new CountryCodeMybatisInterceptor()};
        sessionFactory.setPlugins(plugins);

        return sessionFactory.getObject();
    }
}