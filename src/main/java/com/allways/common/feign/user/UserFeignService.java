package com.allways.common.feign.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFeignService {

    private final UserFeignClient userFeignClient;

    public UserFeignResponse queryUser(Long userSeq) {
        return userFeignClient.queryUser(userSeq);
    }

}
