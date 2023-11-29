package com.allways.domain.user.dto;

import com.allways.common.factory.user.UserUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void UserUpdateRequestValidation() {
        // Given
        UserUpdateRequest updateRequest = UserUpdateRequestFactory.createUserUpdateRequest(
                "TestPassword",
                "TestNickname",
                "Test@example.com",
                "profileImg"
        );

        // When
        Set<ConstraintViolation<UserUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        assertEquals(0, violations.size(), "위반 사항이 없습니다.");
    }

    @Test
    void UserUpdatePasswordValidation() {
        // Given
        UserUpdateRequest updateRequest = UserUpdateRequestFactory.createUserUpdateRequest(
                "",
                "TestNickname",
                "Test@example.com",
                "profileImg"
        );

        // When
        Set<ConstraintViolation<UserUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(), "올바른 비밀번호를 입력하세요.");
    }

    @Test
    void UserUpdateNicknameValidation() {
        // Given
        UserUpdateRequest updateRequest = UserUpdateRequestFactory.createUserUpdateRequest(
                "TestPassword",
                "",
                "Test@example.com",
                "profileImg"
        );

        // When
        Set<ConstraintViolation<UserUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(), "올바른 닉네임을 입력하세요.");
    }

    @Test
    void UserUpdateEmailValidation() {
        // Given
        UserUpdateRequest updateRequest = UserUpdateRequestFactory.createUserUpdateRequest(
                "TestPassword",
                "TestNickname",
                "",
                "profileImg"
        );

        // When
        Set<ConstraintViolation<UserUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(), "올바른 이메일을 입력하세요.");
    }
}
