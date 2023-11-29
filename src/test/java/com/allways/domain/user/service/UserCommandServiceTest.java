package com.allways.domain.user.service;

import com.allways.common.factory.user.UserUpdateRequestFactory;
import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {
    @Mock private UserRepository userRepository;
    @InjectMocks private UserCommandService userCommandService;

    @Test
    void updateUserTest() {
        // Given
        UserUpdateRequest request = UserUpdateRequestFactory.createUserUpdateRequest();
        Long userSeq = 1L;

        // When
        userCommandService.updateUser(request, userSeq);

        // Then
        verify(userRepository).updateByUserSeq(
                userSeq, request.getPassword(), request.getNickname(),
                request.getEmail(), request.getProfileImgSeq()
        );
    }

    @Test
    void deleteUserTest() {
        // Given
        Long userSeq = 1L;

        // When
        userCommandService.deleteUser(userSeq);

        // Then
        verify(userRepository).deleteById(userSeq);
    }
}
