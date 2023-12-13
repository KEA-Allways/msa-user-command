package com.allways.common.feign.fastApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "file-command-service")
public interface FastApiFeignClient {

    @PostMapping(value = "api/feign/profileImg")
    @ResponseStatus(HttpStatus.CREATED)
    void saveProfileImgToFastApi(@RequestBody FastApiUserProfileImgDataRequest request);

    @PutMapping(value = "api/feign/profileImg")
    @ResponseStatus(HttpStatus.CREATED)
    void sendDataForUpdateToFastApiUserProfileImg(@RequestBody FastApiUserProfileImgDataRequest request);
}
