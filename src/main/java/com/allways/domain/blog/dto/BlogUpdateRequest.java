package com.allways.domain.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BlogUpdateRequest {

    @NotBlank(message = "블로그의 이름을 입력해주세요.")
    private String blogName;

    @NotBlank(message = "블로그 소개를 입력해주세요.")
    private String blogDescription;
}
