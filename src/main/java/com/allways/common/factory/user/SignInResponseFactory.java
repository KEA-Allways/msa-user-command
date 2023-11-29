package com.allways.common.factory.user;


import com.allways.domain.user.dto.SignInResponse;

public class SignInResponseFactory {
    public static SignInResponse createSignInResponse(String accessToken, String refreshToken) {
        return new SignInResponse(accessToken, refreshToken);
    }
}
