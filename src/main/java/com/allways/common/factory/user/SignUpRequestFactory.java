package com.allways.common.factory.user;


import com.allways.domain.user.dto.SignUpRequest;

public class SignUpRequestFactory {
    public static SignUpRequest createSignUpRequest() {
        return new SignUpRequest("userId","123456a!","email@email.com",
                "nickname", "profileImgSeq");
    }

    public static SignUpRequest createSignUpRequest(String userId, String password, String email, String nickname, String img) {
        return new SignUpRequest(userId, password,email, nickname, img);
    }

    public static SignUpRequest createSignUpRequestWithUserId(String userId) {
        return new SignUpRequest(userId,"123456a!","email@email.com",  "nickname", "test.jpg");
    }

    public static SignUpRequest createSignUpRequestWithPassword(String password) {
        return new SignUpRequest("userId",password,"email@email.com",  "nickname", "test.jpg");

    }
    public static SignUpRequest createSignUpRequestWithEmail(String email) {
        return new SignUpRequest("userId","123456a!",email,  "nickname", "test.jpg");
    }

    public static SignUpRequest createSignUpRequestWithNickname(String nickname) {
        return new SignUpRequest("userId","123456a!","email@email.com",  nickname, "test.jpg");
    }
}
