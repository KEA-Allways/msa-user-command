package com.allways.common.feign.user;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFeignService {

    private final UserFeignClient userFeignClient;

    public UserFeignResponse queryUser(Long userSeq) {
        return userFeignClient.queryUser(userSeq);
    }

    public UserFeignResponse queryUserByEmail(String email) {
        return userFeignClient.queryUserByEmail(email);
    }
}
