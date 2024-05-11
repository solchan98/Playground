package org.example.springjwt.auth;

import org.example.springjwt.presentation.exception.AuthorizationException;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationManager {

    public void verify(AccessUser accessUser, Roles requiredRoles) {
        if (!accessUser.isAccessible(requiredRoles)) {
            throw new AuthorizationException("권한을 확인하세요.");
        }
    }
}
