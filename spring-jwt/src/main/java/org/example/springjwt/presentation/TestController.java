package org.example.springjwt.presentation;

import static org.example.springjwt.auth.Role.ADMIN;
import static org.example.springjwt.auth.Role.BUYER;
import static org.example.springjwt.auth.Role.READ_ONLY;
import static org.example.springjwt.auth.Role.SELLER;

import org.example.springjwt.auth.RequiredRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    @RequiredRoles(requiredAnyRoles = {ADMIN})
    public String admin() {
        return "hello, admin";
    }

    @RequiredRoles(requiredAnyRoles = {ADMIN, READ_ONLY})
    @GetMapping("/readonly")
    public String readOnly() {
        return "hello, readonly";
    }

    @RequiredRoles(requiredAnyRoles = {ADMIN, SELLER})
    @GetMapping("/seller")
    public String seller() {
        return "hello, seller";
    }

    @RequiredRoles(requiredAnyRoles = {ADMIN, BUYER})
    @GetMapping("/buyer")
    public String buyer() {
        return "hello, buyer";
    }

    @RequiredRoles(requiredAnyRoles = {})
    @GetMapping("/all")
    public String all() {
        return "hello, readonly";
    }
}
