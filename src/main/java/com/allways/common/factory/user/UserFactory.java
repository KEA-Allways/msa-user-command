package com.allways.common.factory.user;

import com.allways.domain.user.entity.User;

public class UserFactory {
    public static User createUser(){
        return new User("userId","123456a!",
                "nickname", "email@email.com", "profileImgSeq");
    }

    public static User createUser(String userId, String password,
                                  String nickname, String email, String profileImgSeq) {
        return new User(userId, password, nickname, email, profileImgSeq);
    }
}
