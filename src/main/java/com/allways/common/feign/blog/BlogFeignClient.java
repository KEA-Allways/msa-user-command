package com.allways.common.feign.blog;

import com.allways.common.feign.user.UserFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msa-user-query" ,url="http://localhost:8084/api/blogs/feign" )
public interface BlogFeignClient {

    @GetMapping("{userSeq}")
    BlogFeignResponse queryBlog(@PathVariable Long userSeq);
}
