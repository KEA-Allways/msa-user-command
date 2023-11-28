package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.service.SignCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignCommandController {
    private final SignCommandService signService;

    @PostMapping("/api/auth/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequest req) {
        signService.signUp(req);
        return success();
    }

    @PostMapping("/api/auth/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody SignInRequest req) {
        return success(signService.signIn(req));
    }
}
