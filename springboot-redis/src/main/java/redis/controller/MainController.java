package redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redis.service.RedisService;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class MainController {

    private final RedisService redisService;

    @PostMapping("")
    public void startRedis(@RequestBody HashMap<String, String> body) {
        redisService.setValues(body.get("name"), body.get("age"));
    }

    @GetMapping("")
    public String startRedis(@RequestParam String name) {
        return redisService.getValues(name);
    }
}
