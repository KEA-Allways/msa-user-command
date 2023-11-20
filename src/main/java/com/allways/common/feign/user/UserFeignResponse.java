package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserFeignResponse {
    private String userId;
    private String nickname;
    private String email;
    private String profileImgSeq;
}
