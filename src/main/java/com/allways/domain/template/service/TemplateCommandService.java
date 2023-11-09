package com.allways.domain.template.service;

import com.allways.domain.template.domain.Template;
import com.allways.domain.template.dto.*;
import com.allways.domain.template.exception.TemplateNotFoundException;
import com.allways.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TemplateCommandService {
    private final TemplateRepository templateRepository;

    // 선택된 templateSeq에 해당하는 template 정보 읽기
    public TemplateDto readOne(Long userSeq, Long templateSeq) {
        return TemplateDto
                .toDto(templateRepository.findByTemplateSeq(templateSeq));
    }

    // 원하는 유저의 모든 template들을 읽어오는 readAll 함수
    // ALL을 땡겨오는거라 날짜와 userSeq까지 가져올거 같긴 한데
    // gpt 도움받은거라 확실하지는 않음 확인 필요
    public TemplateDto[] readAll(Long userSeq) {
        List<Template> allTemplates = templateRepository.findAllByUserSeq(userSeq);
        TemplateDto[] templateDtos = allTemplates.stream()
                .map(TemplateDto::toDto)
                .toArray(TemplateDto[]::new);

        return templateDtos;
    }

    // 선택된 templateSeq에 해당하는 template 삭제
    public void delete(Long templateSeq) {
        Template template = templateRepository.findById(templateSeq)
                .orElseThrow(TemplateNotFoundException::new);
        templateRepository.delete(template);
    }

    // template를 생성
    // TemplateCreateRequest에는 templateName, templateContent, userSeq가 담긴다
    public TemplateCreateResponse create(TemplateCreateRequest req) {
        Template template = templateRepository.save(TemplateCreateRequest.toEntity(req));

        // TemplateCreateResponse에는 생성된 template의 templateSeq가 담긴다
        return new TemplateCreateResponse(template.getTemplateSeq());
    }

    public void updateTemplate(Long templateSeq, TemplateUpdateRequest req) {
        Template template = templateRepository.findById(templateSeq).orElseThrow(TemplateNotFoundException::new);
        template.updateTemplate(req);
    }
}
