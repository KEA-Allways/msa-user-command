package com.allways.domain.template.dto;

import com.allways.domain.template.domain.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {
    private Long templateSeq;
    private String templateName;
    private String templateContent;

    public static TemplateDto toDto(Template template) {
        return new TemplateDto(template.getTemplateSeq(),
                template.getTemplateName(),
                template.getTemplateContent());
    }
}
