package com.allways.domain.user.service;

import com.allways.common.factory.user.SignInRequestFactory;
import com.allways.common.factory.user.SignUpRequestFactory;
import com.allways.common.factory.user.UserFactory;
import com.allways.domain.user.config.TokenHelper;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignInResponse;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SignCommandServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private TokenHelper accessTokenHelper;
    @Mock private TokenHelper refreshTokenHelper;
    @InjectMocks private SignCommandService signCommandService;
    @Captor private ArgumentCaptor<User> UserArgumentCaptor;

    @Test
    void signUpValidation() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest();

        // When
        signCommandService.SignUpForTest(signUpRequest);

        // Then
        verify(userRepository).save(UserArgumentCaptor.capture());

        User savedUser = UserArgumentCaptor.getValue();

        assertEquals(signUpRequest.getUserId(), savedUser.getUserId());
        assertEquals(signUpRequest.getEmail(), savedUser.getEmail());
        assertEquals(signUpRequest.getNickname(), savedUser.getNickname());
        assertEquals(signUpRequest.getProfileImgSeq(), savedUser.getProfileImgSeq());
    }

    @Test
    void signInTest() {
        // Given
        // Mock 객체 및 데이터 생성
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest();
        User user = UserFactory.createUser();

        // When
        // Mock 설정
        when(userRepository.findByEmail(signInRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())).thenReturn(true);

        // 테스트 실행
        SignInResponse signInResponse = signCommandService.signIn(signInRequest);

        // Then
        // 검증
        assertNotNull(signInResponse);
    }
}
