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
public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @InjectMocks private UserService userService;

    // @Test
    // void updateUserTest() {
    //     // Given
    //     Long userSeq = 1L;
    //     UserUpdateRequest request = UserUpdateRequestFactory.createUserUpdateRequest();
    //
    //     // When
    //     userService.updateUserWithPassword(request, userSeq);
    //
    //     // Then
    //     verify(userRepository).updateByUserSeq(
    //             userSeq, request.getPassword(), request.getNickname(),
    //             request.getProfileImgSeq()
    //     );
    // }

    @Test
    void deleteUserTest() {
        // Given
        Long userSeq = 1L;

        // When
        userService.deleteUser(userSeq);

        // Then
        verify(userRepository).deleteById(userSeq);
    }
}
