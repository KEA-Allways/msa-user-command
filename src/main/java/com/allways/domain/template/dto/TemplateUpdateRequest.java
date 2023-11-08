package com.allways.domain.template.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateUpdateRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String templateName;

    @NotBlank(message = "내용을 입력해주세요.")
    private String templateContent;
}
