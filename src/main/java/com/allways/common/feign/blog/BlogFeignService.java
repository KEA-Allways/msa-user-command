package com.allways.common.feign.blog;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogFeignService {

    private final BlogFeignClient blogFeignClient;

    public BlogFeignResponse queryBlog(Long userSeq) {
        return blogFeignClient.queryBlog(userSeq);
    }

}