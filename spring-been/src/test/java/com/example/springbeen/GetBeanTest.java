package com.example.springbeen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("전체 빈 조회하기")
    void getBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", bean = " + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 조회하기")
    void getApplicationBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", bean = " + bean);
            }
        }
    }

    @Test
    @DisplayName("빈의 이름과 타입으로 조회하기")
    void getBeanByNameAndType() {
        StoreService storeService = ac.getBean("storeService", StoreService.class);
        assertThat(storeService).isInstanceOf(StoreServiceImpl.class);
    }

    @Test
    @DisplayName("빈의 이름으로만 조회하기")
    void getBeanByName() {
        Object storeService = ac.getBean("storeService");
        assertThat(storeService).isInstanceOf(StoreServiceImpl.class);
    }

    @Test
    @DisplayName("빈의 타입으로만 조회하기")
    void getBeanByType() {
        StoreService bean = ac.getBean(StoreService.class);
        assertThat(bean).isInstanceOf(StoreServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름과 구체타입으로 조회하기")
    void getBeanByNameAndSpecificType() {
        StoreServiceImpl storeService = ac.getBean("storeService", StoreServiceImpl.class);
        assertThat(storeService).isInstanceOf(StoreServiceImpl.class);
    }

    @Test
    @DisplayName("존재하지 않는 빈의 이름으로 조회하기")
    void getBeanByNotExistBeanName() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxoooxxx", StoreService.class));
    }
}
