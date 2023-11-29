package com.allways.common.factory.user;

import com.allways.domain.user.dto.UserUpdateRequest;

public class UserUpdateRequestFactory {
    public static UserUpdateRequest createUserUpdateRequest() {
        return new UserUpdateRequest("newPassword", "newNickname",
                "newEmail@email.com", "newProfileImgSeq");
    }

    public static UserUpdateRequest createUserUpdateRequest(String newPassword, String newNickname,
                                                            String newEmail, String newProfileImgSeq) {
        return new UserUpdateRequest(newPassword, newNickname, newEmail, newProfileImgSeq);
    }
}
