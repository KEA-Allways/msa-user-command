package com.allways.domain.user.dto;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpRequestTest {

    @Test
    void validateSignUpRequest() {
        // Given
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId("userId");
        signUpRequest.setPassword("Password1!");
        signUpRequest.setEmail("test@example.com");
        signUpRequest.setNickname("UserNickname");
        signUpRequest.setProfileImgSeq("12345");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        Set<String> violations = validator.validate(signUpRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertEquals(0, violations.size(), "Validation should pass for a valid SignUpRequest");
    }
}
