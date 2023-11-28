package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserCommandController {
    private final UserCommandService userCommandService;

    @DeleteMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@RequestHeader(value = "userSeq") Long userSeq){
        userCommandService.deleteUser(userSeq);
        return Response.success();
    }

    @PutMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public Response updateUser(@RequestHeader(value = "userSeq") Long userSeq, @RequestBody UserUpdateRequest req) {
        userCommandService.updateUser(req, userSeq);
        return Response.success();
    }
}
