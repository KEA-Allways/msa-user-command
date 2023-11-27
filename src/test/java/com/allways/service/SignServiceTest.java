package com.allways.service;

import com.allways.domain.user.config.TokenHelper;
import com.allways.domain.user.dto.AccessTokenResponse;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignInResponse;
import com.allways.domain.user.exception.AuthenticationEntryPointException;
import com.allways.domain.user.exception.LoginFailureException;
import com.allways.domain.user.exception.UserEmailAlreadyExistsException;
import com.allways.domain.user.exception.UserNicknameAlreadyExistsException;
import com.allways.domain.user.repository.UserRepository;
import com.allways.domain.user.service.SignService;
import com.allways.domain.user.dto.SignUpRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


import static com.allways.factory.SignInRequestFactory.createSignInRequest;
import static com.allways.factory.SignUpRequestFactory.createSignUpRequest;
import static com.allways.factory.UserFactory.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SignServiceTest {
    SignService signService;
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    TokenHelper accessTokenHelper;
    @Mock
    TokenHelper refreshTokenHelper;
    //SignService에 선언되어있던 @InjectMokcs가 제거되었고,
    // @BeforeEach에서 직접적으로 인스턴스를 생성하여 의존성을 초기화해주고 있습니다.
    @BeforeEach
    void beforeEach(){
        //signService = new SignService(userRepository,passwordEncoder,accessTokenHelper,refreshTokenHelper);
    }

    @Test
    void signUpTest() {
        // given
        SignUpRequest req = createSignUpRequest();

        // when
        signService.signUp(req);

        // then
        verify(passwordEncoder).encode(req.getPassword());
    }

    @Test
    void validateSignUpByDuplicateEmailTest() {
        // given
        given(userRepository.existsByEmail(anyString())).willReturn(true);

        // when, then
        assertThatThrownBy(() -> signService.signUp(createSignUpRequest()))
                .isInstanceOf(UserEmailAlreadyExistsException.class);
    }

    @Test
    void validateSignUpByDuplicateNicknameTest() {
        // given
        given(userRepository.existsByNickname(anyString())).willReturn(true);

        // when, then
        assertThatThrownBy(() -> signService.signUp(createSignUpRequest()))
                .isInstanceOf(UserNicknameAlreadyExistsException.class);
    }



    @Test
    void signInTest() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of((createUser())));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
        given(accessTokenHelper.createToken(anyString())).willReturn("access");
        given(refreshTokenHelper.createToken(anyString())).willReturn("refresh");

        // when
        SignInResponse res = signService.signIn(createSignInRequest("email", "password"));

        // then
        assertThat(res.getAccessToken()).isEqualTo("access");
        assertThat(res.getRefreshToken()).isEqualTo("refresh");
    }

    @Test
    void signInExceptionByNoneMemberTest() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> signService.signIn(createSignInRequest("email", "password")))
                .isInstanceOf(LoginFailureException.class);
    }

    @Test
    void signInExceptionByInvalidPasswordTest() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(createUser()));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(false);

        // when, then
        assertThatThrownBy(() -> signService.signIn(new SignInRequest("email", "password")))
                .isInstanceOf(LoginFailureException.class);
    }

    @Test
    void refreshTokenTest(){
        String refreshToken = "refreshToken";
        String subject = "subject";
        String accessToken = "accessToken";
        given(refreshTokenHelper.validate(refreshToken)).willReturn(true);
        given(refreshTokenHelper.extractSubject(refreshToken)).willReturn(subject);
        given(accessTokenHelper.createToken(subject)).willReturn(accessToken);

        AccessTokenResponse res  = signService.createNewAccessToken(refreshToken);

        assertThat(res.getAccessToken()).isEqualTo(accessToken);
    }

    @Test
    void refreshTokenExceptionByInvalidTokenTest(){
        String refreshToken = "refreshToken";
        given(refreshTokenHelper.validate(refreshToken)).willReturn(false);

        assertThatThrownBy(()->signService.createNewAccessToken(refreshToken))
                .isInstanceOf(AuthenticationEntryPointException.class);
    }


}
