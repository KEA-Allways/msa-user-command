package com.allways.factory;

import com.allways.domain.user.entity.User;

import static java.util.Collections.emptyList;

public class UserFactory {

    public static User createUser(){

        return new User("userId","123456a!","nickname" , "email@email.com", "test.jpg");
    }

    public static User createUser(String userId, String password, String nickname, String email,String img) {
        return new User(userId, password, nickname, email,img);
    }



}
