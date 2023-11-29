package com.allways.domain.blog.dto;

import com.allways.common.factory.blog.BlogCreateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlogCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void BlogCreateRequestValidation() {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory.createBlogCreateRequest(
                "TestBlogName", "TestBlogDescription");

        // When
        Set<ConstraintViolation<BlogCreateRequest>> violations = validator.validate(createRequest);

        // Then
        assertEquals(0, violations.size(), "위반사항이 없습니다.");
    }

    @Test
    void BlogCreateBlogNameValidation() {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory.createBlogCreateRequest(
                "", "TestBlogDescription");

        // When
        Set<ConstraintViolation<BlogCreateRequest>> violations = validator.validate(createRequest);

        // Then
        // blogName은 NotBlank를 위반한다.
        assertEquals(1, violations.size(), "올바른 블로그 이름을 입력해주세요.");
    }

    @Test
    void BlogCreateBlogDescriptionValidation() {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory.createBlogCreateRequest(
                "TestBlogName", "");

        // When
        Set<ConstraintViolation<BlogCreateRequest>> violations = validator.validate(createRequest);

        // Then
        // blogDescription은 NotBlank를 위반한다.
        assertEquals(1, violations.size(), "올바른 블로그 소개를 입력해주세요.");
    }
}
