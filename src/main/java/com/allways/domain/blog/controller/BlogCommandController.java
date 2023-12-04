package com.allways.domain.blog.controller;

import com.allways.common.response.Response;
import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.service.BlogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BlogCommandController {
    private final BlogService blogService;

    @PostMapping("/api/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBlog(@RequestHeader(value = "userSeq") Long userSeq,
                               @RequestBody BlogCreateRequest req) {
        blogService.createBlog(req, userSeq);
        return Response.success();
    }

    @PutMapping("/api/blog/{blogSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateBlog(@PathVariable Long blogSeq,
                               @RequestBody BlogUpdateRequest req) {
        blogService.updateBlog(blogSeq, req);
        return Response.success();
    }

    @DeleteMapping("/api/blog/{blogSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBlog(@PathVariable Long blogSeq) {
        blogService.deleteBlog(blogSeq);
        return Response.success();
    }
}