package com.allways.domain.user.dto;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUpdateRequestTest {

    @Test
    void validateUserUpdateRequest() {
        // Given
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setPassword("TestPassword");
        userUpdateRequest.setNickname("TestNickname");
        userUpdateRequest.setEmail("Test@example.com");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        Set<String> violations = validator.validate(userUpdateRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertEquals(0, violations.size(), "Validation should pass for a valid UserUpdateRequest");
    }
}
