package com.allways.domain.blog.dto;

import com.allways.common.factory.blog.BlogUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlogUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void blogUpdateRequestValidation() {
        // Given
        BlogUpdateRequest updateRequest = BlogUpdateRequestFactory.createBlogUpdateRequest(
                "UpdateBlogName", "UpdateBlogDescription");

        // When
        Set<ConstraintViolation<BlogUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        assertEquals(0, violations.size(), "Validation should pass for a valid BlogUpdateRequest");
    }

    @Test
    void blogUpdateBlogNameValidation() {
        // Given
        BlogUpdateRequest updateRequest = BlogUpdateRequestFactory.createBlogUpdateRequest(
                "", "UpdateBlogDescription");

        // When
        Set<ConstraintViolation<BlogUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        // blogName은 NotBlank를 위반한다.
        assertEquals(1, violations.size(), "올바른 블로그 이름을 입력해주세요.");
    }

    @Test
    void blogUpdateBlogDescriptionValidation() {
        // Given
        BlogUpdateRequest updateRequest = BlogUpdateRequestFactory.createBlogUpdateRequest(
                "UpdateBlogName", "");

        // When
        Set<ConstraintViolation<BlogUpdateRequest>> violations = validator.validate(updateRequest);

        // Then
        // blogDescription은 NotBlank를 위반한다.
        assertEquals(1, violations.size(), "올바른 블로그 소개를 입력해주세요.");
    }
}
