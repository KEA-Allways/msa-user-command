package com.allways.domain.template.dto;

import com.allways.domain.template.domain.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCreateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String templateName;

    @NotBlank(message = "내용을 입력해주세요.")
    private String templateContent;

    // userSeq를 API로 받아야 하기에 일단은 임시로 1L 입력해 둠
    public static Template toEntity(TemplateCreateRequest req) {
        return new Template(req.templateName, req.templateContent, 1L);
    }
}
