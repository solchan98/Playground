package org.example.springsecurityjwt.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public final class RequestMatchers {

    private RequestMatchers() {
    }

    public static final AntPathRequestMatcher LOGIN = new AntPathRequestMatcher("/login", HttpMethod.POST.name());

    public static final AntPathRequestMatcher REFRESH_TOKEN = new AntPathRequestMatcher("/refresh", HttpMethod.POST.name());

    public static final AntPathRequestMatcher PERMIT_ALL = new AntPathRequestMatcher("/authenticated");

    public static final AntPathRequestMatcher OPEN_API = new AntPathRequestMatcher("/permit-all");

    public static final AntPathRequestMatcher ADMIN = new AntPathRequestMatcher("/admin");

    public static final AntPathRequestMatcher SELLER = new AntPathRequestMatcher("/seller");

    public static final AntPathRequestMatcher BUYER = new AntPathRequestMatcher("/buyer");
}
