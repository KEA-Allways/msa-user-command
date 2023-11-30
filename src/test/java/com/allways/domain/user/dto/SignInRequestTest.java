package com.allways.domain.user.dto;

import com.allways.common.factory.user.SignInRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void signInRequestValidation() {
        // Given
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest();

        // When
        Set<ConstraintViolation<SignInRequest>> violations = validator.validate(signInRequest);

        // Then
        assertEquals(0, violations.size(), "모든 조건을 만족합니다.");
    }

    @Test
    void signInEmailValidation() {
        // Given
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest(
                "", "password");

        // When
        Set<ConstraintViolation<SignInRequest>> violations = validator.validate(signInRequest);

        // Then
        assertEquals(1, violations.size(), "이메일은 Blank가 아니어야합니다.");
    }

    @Test
    void signInPasswordValidation() {
        // Given
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest(
                "email@email.com", "");

        // When
        Set<ConstraintViolation<SignInRequest>> violations = validator.validate(signInRequest);

        // Then
        assertEquals(1, violations.size(), "비밀번호는 Blank가 아니어야합니다.");
    }
}
