package com.allways.domain.blog.dto;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlogCreateRequestTest {

    @Test
    void validateBlogCreateRequest() {
        // Given
        BlogCreateRequest blogCreateRequest = new BlogCreateRequest();
        blogCreateRequest.setBlogName("TestBlogName");
        blogCreateRequest.setBlogDescription("TestBlogDescription");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        Set<String> violations = validator.validate(blogCreateRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertEquals(0, violations.size(), "Validation should pass for a valid BlogCreateRequest");
    }
}
