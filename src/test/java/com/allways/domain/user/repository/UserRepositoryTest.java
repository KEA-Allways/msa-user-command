package com.allways.domain.user.repository;

import com.allways.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void updateByUserSeqTest() {
        // Given
        String newPassword = "newPassword3";
        String newNickname = "newNickname3";
        String newEmail = "newEmail3@example.com";
        String newProfileImgSeq = "newProfileImgSeq3";

        User user = new User("userId5", "password5",
                "nickname5", "email5@example.com", "profileImgSeq5");
        userRepository.save(user);

        // When
        User updatedUser = userRepository.findById(user.getUserSeq()).orElse(null);
        if (updatedUser != null) {
            updatedUser.setPassword(newPassword);
            updatedUser.setNickname(newNickname);
            updatedUser.setEmail(newEmail);
            updatedUser.setProfileImgSeq(newProfileImgSeq);
            userRepository.save(updatedUser);
        }

        // Then
        User fetchedUpdatedUser = userRepository.findById(user.getUserSeq()).orElse(null);
        assertThat(fetchedUpdatedUser).isNotNull();
        assertThat(fetchedUpdatedUser.getPassword()).isEqualTo(newPassword);
        assertThat(fetchedUpdatedUser.getNickname()).isEqualTo(newNickname);
        assertThat(fetchedUpdatedUser.getEmail()).isEqualTo(newEmail);
        assertThat(fetchedUpdatedUser.getProfileImgSeq()).isEqualTo(newProfileImgSeq);

        userRepository.deleteById(updatedUser.getUserSeq());
    }
}

