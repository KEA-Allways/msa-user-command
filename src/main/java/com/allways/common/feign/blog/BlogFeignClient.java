package com.allways.common.feign.blog;

import com.allways.common.feign.user.UserFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="blog-query-service" ,url="${env.user-feign-url}")
public interface BlogFeignClient {

    @GetMapping("{userSeq}")
    BlogFeignResponse queryBlog(@PathVariable Long userSeq);
}
