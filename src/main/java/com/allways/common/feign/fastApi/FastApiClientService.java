package com.allways.common.feign.fastApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FastApiClientService {

    private final FastApiFeignClient fastApiFeignClient;


    public void sendDataToFastApiUserProfileImg(Long userSeq, String imageUrl){
        FastApiUserProfileImgDataRequest request = new FastApiUserProfileImgDataRequest(userSeq, imageUrl);
        fastApiFeignClient.sendDataToFastApiUserProfileImg(request);
    }

    public void sendDataForUpdateToFastApiUserProfileImg(Long userSeq,String imageUrl){
        FastApiUserProfileImgDataRequest request = new FastApiUserProfileImgDataRequest(userSeq, imageUrl);
        fastApiFeignClient.sendDataForUpdateToFastApiUserProfileImg(request);
    }
}
