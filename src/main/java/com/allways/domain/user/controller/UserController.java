package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@PathVariable Long id ){
        return Response.success(userService.read(id));
    }

    @DeleteMapping("/api/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long id ){
        userService.delete(id);
        return Response.success();
    }
}
