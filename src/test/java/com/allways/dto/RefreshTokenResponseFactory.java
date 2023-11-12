package com.allways.dto;


import com.allways.domain.user.dto.RefreshTokenResponse;

public class RefreshTokenResponseFactory {
    public static RefreshTokenResponse createRefreshTokenResponse(String accessToken) {
        return new RefreshTokenResponse(accessToken);
    }
}
