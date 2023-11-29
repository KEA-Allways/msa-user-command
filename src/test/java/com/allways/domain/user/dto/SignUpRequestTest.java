package com.allways.domain.user.dto;

import com.allways.common.factory.user.SignUpRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void signUpRequestValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest();

        // When
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // Then
        assertEquals(0, violations.size(), "모든 조건을 만족합니다.");
    }

    @Test
    void signUpUserIdValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest(
                "",
                "Password1!",
                "test@example.com",
                "UserNickname",
                "12345"
        );

        // When
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // Then
        assertEquals(1, violations.size(), "올바른 이메일을 입력하셔야 합니다.");
    }

    @Test
    void signUpPasswordValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest(
                "userId",
                "",
                "test@example.com",
                "UserNickname",
                "12345"
        );

        // When
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // Then
        // 비밀번호도 Blank와 Pattern의 2가지 제약 조건을 가지고 있는데 ""는 2개를 다 어긴다.
        assertEquals(2, violations.size(), "올바른 비밀번호를 입력하셔야 합니다.");
    }

    @Test
    void signUpEmailValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest(
                "userId",
                "Password1!",
                "",
                "UserNickname",
                "12345"
        );

        // When
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // Then
        assertEquals(1, violations.size(), "올바른 이메일을 입력하셔야 합니다.");
    }

    @Test
    void signUpNicknameValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest(
                "userId",
                "Password1!",
                "test@example.com",
                "",
                "12345"
        );

        // When
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // Then
        // 닉네임의 조건이 3개인데 (Blank, Size, Pattern) ""로 입력하면 3개다 위반함
        assertEquals(3, violations.size(), "올바른 닉네임을 입력하셔야 합니다.");
    }

    @Test
    void signUpImgValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest(
                "userId",
                "Password1!",
                "test@example.com",
                "UserNickname",
                ""
        );

        // When
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // Then
        assertEquals(1, violations.size(), "올바른 프로필 이미지를 넣어주세요.");
    }
}
