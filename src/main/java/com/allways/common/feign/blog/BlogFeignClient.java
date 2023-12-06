package com.allways.common.feign.blog;

import com.allways.common.feign.user.UserFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="blog-query-service")
public interface BlogFeignClient {

    @GetMapping("api/blogs/feign/{userSeq}")
    BlogFeignResponse queryBlog(@PathVariable Long userSeq);


}
