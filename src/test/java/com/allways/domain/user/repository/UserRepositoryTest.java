package com.allways.domain.user.repository;

import com.allways.common.factory.user.UserUpdateRequestFactory;
import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    void updateByUserSeqTest() {
        // Given
        Long userSeq = 3L;
        UserUpdateRequest updateRequest = UserUpdateRequestFactory
                .createUserUpdateRequest();

        // When
        userRepository.updateByUserSeq(
                userSeq,
                updateRequest.getPassword(),
                updateRequest.getNickname(),
                updateRequest.getEmail(),
                updateRequest.getProfileImgSeq()
        );

        User updatedUser = userRepository.getById(userSeq);

        // Then
        assertEquals(updateRequest.getNickname(), updatedUser.getNickname());
        assertEquals(updateRequest.getEmail(), updatedUser.getEmail());
        assertEquals(updateRequest.getProfileImgSeq(), updatedUser.getProfileImgSeq());
    }
}

