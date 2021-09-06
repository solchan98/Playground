package com.springbootswagger.controller;

import com.springbootswagger.domain.LoginDTO;
import com.springbootswagger.domain.User;
import com.springbootswagger.domain.UserDTO;
import com.springbootswagger.jwt.JwtTokenProvider;
import com.springbootswagger.service.RedisService;
import com.springbootswagger.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Api(value = "UserController V1")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RedisService redisService;
    ////private final TokenRepository tokenRepository;

    // 회원가입
    @ApiOperation(value = "회원가입", notes = "회원가입 요청")
    @ApiResponses({@ApiResponse(code = 200, message = "회원가입을 축하합니다.")})
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserDTO user) {
        Integer result = userService.join(user);
        return result != null ?
                ResponseEntity.ok().body("회원가입을 축하합니다!") :
                ResponseEntity.badRequest().build();
    }

    // 로그인
    @ApiOperation(value = "로그인", notes = "로그인 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공!"),
            @ApiResponse(code = 400, message = "아이디 혹은 비밀번호가 잘못되었습니다.")})
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO user, HttpServletResponse response) {
        // 유저 존재 확인
        User member = userService.findUserByEmail(user.getEmail());
        // 비밀번호 체크
        userService.checkPassword(member.getPassword(), user.getPassword());
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
    @ApiOperation(value = "로그아웃", notes = "로그아웃 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공!")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true,
                    dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "refreshToken", value = "refreshToken", required = true,
                    dataType = "string", paramType = "header")
    })
    @GetMapping("/api/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        redisService.delValues(request.getHeader("refreshToken"));
        return ResponseEntity.ok().body("로그아웃 성공!");
    }

    // 나의 정보 조회
    @ApiOperation(value = "나의 정보 조회", notes = "나의 정보 조회하기")
    @ApiResponses({
//            @ApiResponse(code = 200, message = user),
            @ApiResponse(code = 500, message = "서버에서 문제가 발생하였습니다.")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true,
                    dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "refreshToken", value = "refreshToken", required = true,
                    dataType = "string", paramType = "header")
    })
    @GetMapping("/profile")
    public UserDTO test(@AuthenticationPrincipal User user) {
        return userService.getProfile(user);
    }

}