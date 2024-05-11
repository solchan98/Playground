package org.example.springjwt.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationManager authorizationManager;

    public AuthInterceptor(AuthenticationManager authenticationManager, AuthorizationManager authorizationManager) {
        this.authenticationManager = authenticationManager;
        this.authorizationManager = authorizationManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        RequiredRoles methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(RequiredRoles.class);
        if (methodAnnotation == null) {
            return true;
        }

        String token = request.getHeader("Authorization")
                .replace("Bearer ", "")
                .replace("bearer ", "");

        AccessUser accessUser = authenticationManager.verify(new JwtAuthentication(token));
        authorizationManager.verify(accessUser, Roles.from(methodAnnotation.requiredAnyRoles()));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
