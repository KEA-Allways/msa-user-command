package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;

    @PostMapping("/api/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    //요청으로 전달받는 JSON 바디를 객체로 변환하기 위해 @RequestBody를 선언
    // Request 객체의 필드 값을 검증하기 위해 @Valid를 선언해줍니다.
    public Response signUp(@Valid @RequestBody SignUpRequest req) {
        signService.signUp(req);
        return success();
    }

    @PostMapping("/api/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody SignInRequest req) {
        return success(signService.signIn(req));
    }

    // 파라미터에 설정된 @RequestHeader는 required 옵션의 기본 설정 값이 true이기 때문에,
    // 이 헤더 값이 전달되지 않았을 때 예외가 발생하게 됩니다.
    // 현재 API를 통해 refreshToken을 요청하는 경우는 없는 것 같고
    // refreshToken을 쓸 때는 gateway에서 accessToken이 만료되어 refreshToken을 사용해 재발급하려 하는데
    // refreshToken도 만료된 경우 해당 API를 실행해야 한다.
    @PostMapping("/api/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public Response refreshToken(@RequestHeader(value = "Authorization") String refreshToken){
        return success(signService.refreshToken(refreshToken));
    }
}
