package com.allways.domain.user.service;

import com.allways.domain.user.dto.UserDto;
import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateUser(UserUpdateRequest req, Long userSeq) {
        userRepository.updateById(userSeq, req.getPassword(),
                req.getNickname(), req.getEmail(),
                req.getProfileImgSeq());
    }

    @Transactional
    public void deleteUser(Long userSeq) {
        userRepository.deleteById(userSeq);
    }
}
