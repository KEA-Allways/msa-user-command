package com.allways.domain.user.service;

import com.allways.domain.user.dto.UserDto;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

//    public UserDto read(Long userSeq){
//        return UserDto.toDto(userRepository.findById(userSeq))
//    }
}