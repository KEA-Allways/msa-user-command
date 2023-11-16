package com.allways.domain.user.exception;

public class UserNicknameAlreadyExistsException extends RuntimeException{
    public UserNicknameAlreadyExistsException(String message) {
        super(message);
    }
}