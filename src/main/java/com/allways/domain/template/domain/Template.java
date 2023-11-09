package com.allways.domain.template.domain;

import com.allways.common.EntityDate;
import com.allways.domain.template.dto.TemplateUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 현재 Template에는 이미지가 들어가지 않는다고 가정하고 만들고 있음
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Template extends EntityDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateSeq;

    @Column(nullable = false, length = 20)
    private String templateName;

    // Content를 무슨 타입으로 둬야 할까
    @Column(nullable = false, length = 10000)
    private String templateContent;

    // API로 받아서 저장해주는 userSeq -> FK임
    // 블로그 글에서는 모놀리식이라 Member member를 그대로 @JoinColumn으로 박아버리던데
    // 우리는 msa 구조라 일단은 userSeq만 가지고 있는 관계로 그냥 @Column으로 두는게 맞는지 잘 모르겠음
    @Column(nullable = false)
    private Long userSeq;

    public Template(String templateName, String templateContent, Long userSeq) {
        this.templateName = templateName;
        this.templateContent = templateContent;
        this.userSeq = userSeq;
    }

    public void updateTemplate(TemplateUpdateRequest req) {
        this.templateName = req.getTemplateName();
        this.templateContent = req.getTemplateContent();
    }
}
