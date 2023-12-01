package com.allways.domain.user.service;

import com.allways.common.factory.user.SignUpRequestFactory;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignServiceTest {
    @Mock private UserRepository userRepository;
    @InjectMocks private SignService signService;
    @Captor private ArgumentCaptor<User> UserArgumentCaptor;

    @Test
    void signUpTest() {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest();

        // When
        signService.SignUpForTest(signUpRequest);

        // Then
        verify(userRepository).save(UserArgumentCaptor.capture());

        User savedUser = UserArgumentCaptor.getValue();

        assertEquals(signUpRequest.getUserId(), savedUser.getUserId());
        assertEquals(signUpRequest.getEmail(), savedUser.getEmail());
        assertEquals(signUpRequest.getNickname(), savedUser.getNickname());
        assertEquals(signUpRequest.getProfileImgSeq(), savedUser.getProfileImgSeq());
    }
    
    // feign으로 인해 signIn test가 안됨
}
