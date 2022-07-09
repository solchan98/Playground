package redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.domain.User;
import redis.domain.UserDTO;
import redis.jwt.JwtTokenProvider;
import redis.service.RedisService;
import redis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RedisService redisService;
    ////private final TokenRepository tokenRepository;

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
        // 어세스, 리프레시 토큰 발급 및 헤더 설정
        String accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail(), member.getRoles());
        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        // Redis 인메모리에 리프레시 토큰 저장
        redisService.setValues(refreshToken, member.getEmail());
        // 리프레시 토큰 저장소에 저장
        ////tokenRepository.save(new RefreshToken(refreshToken));

        return ResponseEntity.ok().body("로그인 성공!");
    }

    // 로그아웃
    @GetMapping("/api/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        redisService.delValues(request.getHeader("refreshToken"));
        return ResponseEntity.ok().body("로그아웃 성공!");
    }

    // JWT 인증 요청 테스트
    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        return "Hello, User?";
    }

}