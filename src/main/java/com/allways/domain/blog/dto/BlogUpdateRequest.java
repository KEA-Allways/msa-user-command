package com.allways.domain.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUpdateRequest {
    @NotBlank(message = "블로그의 이름을 입력해주세요.")
    private String blogName;

    @NotBlank(message = "블로그 소개를 입력해주세요.")
    private String blogDescription;
}
