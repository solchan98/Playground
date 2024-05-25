package com.example.springbeen;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle1 implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        // 커넥션 해제 진행
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 커넥션 연결 진행
    }
}
