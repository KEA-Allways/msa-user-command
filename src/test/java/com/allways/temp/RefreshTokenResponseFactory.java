package com.allways.temp;


import com.allways.domain.user.dto.AccessTokenResponse;

public class RefreshTokenResponseFactory {
    public static AccessTokenResponse createRefreshTokenResponse(String accessToken) {
        return new AccessTokenResponse(accessToken);
    }
}
