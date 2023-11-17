package com.allways.domain.blog.dto;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.user.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlogCreateRequest {

    @NotBlank(message = "블로그 이름을 입력해주세요.")
    private String blogName;
    
    @NotBlank(message = "블로그 소개를 입력해주세요.")
    private String blogDescription;
    
    // 유저 정보는 어디서 가져오나
    public Blog toEntity(BlogCreateRequest req, Long userSeq) {
        return new Blog(req.blogName, req.blogDescription, userSeq);
    }
}
