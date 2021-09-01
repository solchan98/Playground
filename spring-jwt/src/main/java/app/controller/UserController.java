package app.controller;

import app.domain.User;
import app.domain.UserDTO;
import app.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserDTO user) {
        Integer result = userService.join(user);
        return result != null ?
                ResponseEntity.ok().body("회원가입을 축하합니다!") :
                ResponseEntity.badRequest().build();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO user, HttpServletResponse response) {
        // 유저 존재 확인
        User member = userService.findUser(user);
        // 비밀번호 체크
        userService.checkPassword(member, user);
        // 토큰 생성 및 응답
        String token = jwtTokenProvider.createToken(member.getEmail(), member.getRoles());
        response.setHeader("authorization", "bearer " + token);
        return ResponseEntity.ok().body("로그인 성공!");
    }

    // JWT 인증 요청 테스트
    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        return "Hello, User?";
    }

    // 통합 예외 핸들러
    @ExceptionHandler
    public String exceptionHandler(Exception exception) {
        return exception.getMessage();
    }
}