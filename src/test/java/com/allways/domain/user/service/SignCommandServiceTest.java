package com.allways.domain.user.service;

import com.allways.common.factory.user.SignInRequestFactory;
import com.allways.common.factory.user.SignUpRequestFactory;

import com.allways.common.factory.user.UserFactory;
import com.allways.domain.user.config.TokenHelper;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignInResponse;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.LoginFailureException;
import com.allways.domain.user.exception.UserEmailAlreadyExistsException;
import com.allways.domain.user.exception.UserNicknameAlreadyExistsException;
import com.allways.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void signUp_Successful() {
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest();

        signCommandService.signUp(signUpRequest);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void signUp_AlreadyExistsByNickName() {
        // 가짜 객체 생성
        SignUpRequest signUpRequestWithSameNickName = SignUpRequestFactory.createSignUpRequest("rcm", "rcm0128", "woody121@allways.com", "woody", null);

        // 닉네임이 이미 존재한다고 설정
        when(userRepository.existsByNickname("woody")).thenReturn(true);

        // 특정 닉네임을 가진 회원가입 요청 시 예외가 발생하는지 확인
        assertThrows(UserNicknameAlreadyExistsException.class,
                () -> signCommandService.signUp(signUpRequestWithSameNickName));

        // 닉네임이 중복되는지 userRepository의 existsByNickname 메서드가 한 번 호출되는지 확인
        verify(userRepository, times(1)).existsByNickname("woody");
    }

    @Test
    void signUp_AlreadyExistsByEmail() {
        // Mock 객체 생성
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest("rcm1234", "rcm0128", "woody@allways.com", "woody12", null);

        // 이메일이 이미 존재한다고 설정
        when(userRepository.existsByEmail("woody@allways.com")).thenReturn(true);

        // 이메일 중복 시 예외가 발생하는지 확인
        assertThrows(UserEmailAlreadyExistsException.class,
                () -> signCommandService.signUp(signUpRequest));

        // userRepository의 existsByEmail 메서드가 한 번 호출되는지 확인
        verify(userRepository, times(1)).existsByEmail("woody@allways.com");
    }

    @Test
    void signIn_Successful() {
        // Mock 객체 및 데이터 생성
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest();
        User user = UserFactory.createUser();

        // Mock 설정
        when(userRepository.findByEmail(signInRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())).thenReturn(true);

        // 테스트 실행
        SignInResponse signInResponse = signCommandService.signIn(signInRequest);

        // 검증
        assertNotNull(signInResponse);
    }

    @Test
    void signIn_Failure() {
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest();
        User user = UserFactory.createUser();

        when(userRepository.findByEmail(signInRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(LoginFailureException.class,
                () -> signCommandService.signIn(signInRequest));
    }
}
