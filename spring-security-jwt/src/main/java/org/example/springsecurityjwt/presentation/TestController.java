package org.example.springsecurityjwt.presentation;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @GetMapping("/test/all")
    public Map<String, String> all() {
        return Map.of("data", "hello, all");
    }

    @GetMapping("/seller")
    public Map<String, String> seller() {
        return Map.of("data", "hello, seller");
    }

    @GetMapping("/buyer")
    public Map<String, String> buyer() {
        return Map.of("data", "hello, buyer");
    }

    @GetMapping("/admin")
    public Map<String, String> admin() {
        return Map.of("data", "hello, admin");
    }
}
