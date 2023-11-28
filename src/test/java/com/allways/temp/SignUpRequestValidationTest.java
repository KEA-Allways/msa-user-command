package com.allways.temp;

import com.allways.domain.user.dto.SignUpRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.allways.common.factory.user.SignUpRequestFactory.*;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class SignUpRequestValidationTest {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void validateTest(){
        SignUpRequest req = createSignUpRequest();
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        assertThat(validate).isEmpty();
    }

    @Test
    void invalidateByNotFormattedEmailTest(){
        String invalidValue="email";
        SignUpRequest req = createSignUpRequestWithEmail(invalidValue);
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);
        //에러 발생해야 해서 empty 면 안됨
        assertThat(validate).isNotEmpty();
        // 해당 invalidValue 관련한 에러가 있어야함
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }
    @Test
    void invalidateByEmailTest() {
        String invalidValue = null;
        SignUpRequest req = createSignUpRequestWithEmail(invalidValue);

        Set<ConstraintViolation<SignUpRequest>>validate = validator.validate(req);

        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByBlankEmailTest(){
        String invalidValue = " ";
        SignUpRequest req =createSignUpRequestWithEmail(invalidValue);

        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByEmptyPasswordTest(){
        String invalidValue = null;
        SignUpRequest req = createSignUpRequestWithPassword(invalidValue);

        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByBlankPasswordTest() {
        String invalidValue = "         ";
        SignUpRequest req = createSignUpRequestWithPassword(invalidValue);

        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByShortPasswordTest(){
        String invalidValue = "12312a!";
        SignUpRequest req = createSignUpRequestWithPassword(invalidValue);

        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }
    @Test
    void invalidateByNoneAlphabetPasswordTest() {
        String invalidValue = "1234!@#12334";
        SignUpRequest req = createSignUpRequestWithPassword(invalidValue);

        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByNoneSpecialCasePasswordTest() {
        // given
        String invalidValue = "abc123abc";
        SignUpRequest req = createSignUpRequestWithPassword(invalidValue);

        // when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        // then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByEmptyNicknameTest() {
        // given
        String invalidValue = null;
        SignUpRequest req = createSignUpRequestWithNickname(invalidValue);

        // when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        // then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByBlankNicknameTest() {
        // given
        String invalidValue = " ";
        SignUpRequest req = createSignUpRequestWithNickname(invalidValue);

        // when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        // then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByShortNicknameTest() {
        // given
        String invalidValue = "한";
        SignUpRequest req = createSignUpRequestWithNickname(invalidValue);

        // when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        // then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }

    @Test
    void invalidateByNotAlphabetOrHangeulNicknameTest() {
        // given
        String invalidValue = "송2jae";
        SignUpRequest req = createSignUpRequestWithNickname(invalidValue);

        // when
        Set<ConstraintViolation<SignUpRequest>> validate = validator.validate(req);

        // then
        assertThat(validate).isNotEmpty();
        assertThat(validate.stream().map(v -> v.getInvalidValue()).collect(toSet())).contains(invalidValue);
    }
}
