package org.example.springsecurityjwt;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/permit-all")
    public Map<String, String> openApi() {
        return Map.of("data", "hello, all");
    }

    @GetMapping("/authenticated")
    public Map<String, String> permitAll() {
        return Map.of("data", "hello, members");
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
