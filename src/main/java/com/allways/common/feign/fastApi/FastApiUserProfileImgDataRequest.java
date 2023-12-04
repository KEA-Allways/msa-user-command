package com.allways.common.feign.fastApi;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class FastApiUserProfileImgDataRequest {

    private Long userSeq;
    private String imageUrl;

    FastApiUserProfileImgDataRequest(Long userSeq, String imageUrl){
        this.userSeq = userSeq;
        this.imageUrl = imageUrl;
    }
}