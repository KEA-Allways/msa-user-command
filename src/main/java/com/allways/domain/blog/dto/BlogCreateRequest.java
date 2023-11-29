package com.allways.domain.blog.dto;

import com.allways.domain.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateRequest {
    @NotBlank(message = "블로그 이름을 입력해주세요.")
    private String blogName;
    
    @NotBlank(message = "블로그 소개를 입력해주세요.")
    private String blogDescription;

    public Blog toEntity(BlogCreateRequest req, Long userSeq) {
        return new Blog(req.blogName, req.blogDescription, userSeq);
    }
}
