package com.example.mybatisglobalparams;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CountryCodeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String countryCode = request.getHeader("Country-Code");  // Header에서 국가 코드 추출
        CountryCodeContext.setCountryCode(countryCode);  // ThreadLocal에 저장
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("CountryCodeInterceptor afterCompletion");
        CountryCodeContext.clear();  // 요청 완료 후 ThreadLocal 초기화
    }
}
