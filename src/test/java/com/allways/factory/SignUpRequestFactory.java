package com.allways.factory;


import com.allways.domain.user.dto.SignUpRequest;

public class SignUpRequestFactory {
    public static SignUpRequest createSignUpRequest() {
        return new SignUpRequest("userId","123456a!","email@email.com",  "nickname", "test.jpg");
    }

    public static SignUpRequest createSignUpRequest(String userId, String password,String email, String username, String img) {
        return new SignUpRequest(userId, password,email, username, img);
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
