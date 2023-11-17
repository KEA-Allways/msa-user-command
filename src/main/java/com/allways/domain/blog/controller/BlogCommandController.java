package com.allways.domain.blog.controller;

import com.allways.common.response.Response;
import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.service.BlogCommandService;
import com.allways.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BlogCommandController {
    private final BlogCommandService blogCommandService;

    // Blog Create
    // 생성 후 생성된 BlogSeq를 return 한다.
    @PostMapping("/api/blogs/new-blog/{userSeq}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBlog(@PathVariable Long userSeq, @RequestBody BlogCreateRequest req) {
        return Response.success(blogCommandService.create(req, userSeq));
    }

    @PutMapping("/api/blogs/{blogSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateBlog(@PathVariable Long blogSeq,
                           @RequestBody BlogUpdateRequest req) {
        blogCommandService.update(blogSeq, req);
        return Response.success();
    }

    @DeleteMapping("/api/blogs/{blogSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBlog(@PathVariable Long blogSeq) {
        blogCommandService.delete(blogSeq);
        return Response.success();
    }
}