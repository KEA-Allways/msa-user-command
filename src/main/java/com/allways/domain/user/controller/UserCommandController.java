package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserCommandController {
    private final UserService userService;

    @DeleteMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@RequestHeader(value = "userSeq") Long userSeq){
        userService.deleteUser(userSeq);
        return Response.success();
    }

    @PutMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public Response updateUser(@RequestHeader(value = "userSeq") Long userSeq, @RequestBody UserUpdateRequest req) {
        userService.updateUser(req, userSeq);
        return Response.success();
    }



}
