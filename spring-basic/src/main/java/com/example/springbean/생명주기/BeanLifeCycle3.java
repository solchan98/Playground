package com.example.springbean.생명주기;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle3 {

    @PostConstruct
    public void init() {
        // 커넥션 해제 진행
    }

    @PreDestroy
    public void destroy() {
        // 커넥션 연결 진행
    }
}
