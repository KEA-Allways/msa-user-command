package com.allways.domain.user.service;

import com.allways.common.feign.fastApi.FastApiClientService;
import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FastApiClientService fastApiClientService;

    @Transactional
    public void updateUserWithPassword(UserUpdateRequest req, Long userSeq) {
        String password =passwordEncoder.encode(req.getPassword());
        userRepository.updateByUserSeq(userSeq, password,
                req.getNickname(),req.getProfileImgSeq());
        fastApiClientService.sendDataForUpdateToFastApiUserProfileImg(userSeq, req.getProfileImgSeq());
    }

    @Transactional
    public void updateUserWithoutPassword(UserUpdateRequest req, Long userSeq) {
        userRepository.updateByUserSeqWithoutPassword(userSeq, req.getNickname(),req.getProfileImgSeq());
        fastApiClientService.sendDataForUpdateToFastApiUserProfileImg(userSeq, req.getProfileImgSeq());
    }

    @Transactional
    public void deleteUser(Long userSeq) {
        userRepository.deleteById(userSeq);
    }
}
