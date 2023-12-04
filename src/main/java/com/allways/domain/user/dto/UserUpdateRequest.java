package com.allways.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank(message = "새로운 비밀번호를 입력하세요.")
    private String password;

    @NotBlank(message = "새로운 닉네임을 입력하세요.")
    private String nickname;

    @NotBlank(message = "새로운 이메일을 입력하세요.")
    private String email;

    @NotBlank(message = "새로운 프로필을 입력해주세요.")
    private String profileImgSeq;
}