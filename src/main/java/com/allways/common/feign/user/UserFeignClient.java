package com.allways.common.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msa-user-query" ,url="http://localhost:8084/api/users/feign" )
public interface UserFeignClient {

    @GetMapping("{userSeq}")
    UserFeignResponse queryUser(@PathVariable Long userSeq);

}
