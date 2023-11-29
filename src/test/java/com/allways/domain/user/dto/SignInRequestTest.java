package com.allways.domain.user.dto;

import com.allways.common.factory.user.SignInRequestFactory;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInRequestTest {

    @Test
    void signInRequestValidation() {
        // Arrange
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest();

        // Validator 설정
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Act
        Set<String> violations = validator.validate(signInRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Assert
        assertEquals(0, violations.size(), "Validation should pass for a valid SignInRequest");
    }
}
