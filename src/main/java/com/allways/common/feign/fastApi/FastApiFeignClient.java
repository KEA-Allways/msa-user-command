package com.allways.common.feign.fastApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "file-command-service", url = "api/feign")
public interface FastApiFeignClient {

    @PostMapping(value = "/profileImg")
    @ResponseStatus(HttpStatus.CREATED)
    void saveProfileImgToFastApi(@RequestBody FastApiUserProfileImgDataRequest request);

    @PutMapping(value = "/receive_userProfileImg")
    @ResponseStatus(HttpStatus.CREATED)
    void sendDataForUpdateToFastApiUserProfileImg(@RequestBody FastApiUserProfileImgDataRequest request);
}