package com.allways.common.factory.user;

import com.allways.domain.user.dto.UserUpdateRequest;

public class UserUpdateRequestFactory {
    public static UserUpdateRequest createUserUpdateRequest() {
        return new UserUpdateRequest("newPassword12", "newNickname12",
                 "newProfile12ImgSeq");
    }

    public static UserUpdateRequest createUserUpdateRequest(String newPassword, String newNickname, String newProfileImgSeq) {
        return new UserUpdateRequest(newPassword, newNickname, newProfileImgSeq);
    }
}
