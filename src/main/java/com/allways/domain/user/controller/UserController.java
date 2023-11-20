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
public class UserController {
    private final UserService userService;

    @DeleteMapping("/api/members/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@PathVariable Long userSeq){
        userService.deleteUser(userSeq);
        return Response.success();
    }

    // updateUser를 일단 틀만 추가해 봤습니다.
    // userUpdateRequest의 필요 내용으로
    // 1. password, 2. nickname, 3. email, 4. profileImgSeq 로 총 4개로 지정해놨고
    // 추후에 변경해서 사용해도 됩니다.
    // 가져오는 방식은 RequestBody 방식이기에 헤더가 아닌 body에 담아서 프론트에서 전송하면 됩니다.
    @PutMapping("/api/members/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateUser(@RequestBody UserUpdateRequest req,
                               @PathVariable Long userSeq) {
        userService.updateUser(req, userSeq);
        return Response.success();
    }
}
