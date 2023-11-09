package com.allways.domain.template.controller;

import com.allways.common.response.Response;
import com.allways.domain.template.dto.TemplateCreateRequest;
import com.allways.domain.template.dto.TemplateUpdateRequest;
import com.allways.domain.template.service.TemplateCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TemplateCommandController {
    private final TemplateCommandService templateCommandService;

    // 템플릿(서식) 목록 불러오기
    @GetMapping("/api/templates/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(@PathVariable Long userSeq) {
        return Response.success(templateCommandService.readAll(userSeq));
    }

    // 템플릿(서식) 한개 내용물 불러오기
    @GetMapping("/api/templates/{userSeq}/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readOne(@PathVariable Long userSeq, @PathVariable Long templateSeq) {
        return Response.success(templateCommandService.readOne(userSeq, templateSeq));
    }

    // 템플릿(서식) 생성하기
    @PostMapping("/api/templates/new-template")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ModelAttribute TemplateCreateRequest req) {
        Response.success(templateCommandService.create(req));
    }

    // 선택된 템플릿(서식) Seq에 해당하는 template 삭제하기
    @DeleteMapping("/api/templates/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long templateSeq) {
        templateCommandService.delete(templateSeq);
        return Response.success(templateSeq);
    }

    // 선택된 템플릿(서식) Seq에 해당하는 template 수정하기(update)
    // TemplateUpdateRequest의 내용물은 templateName과 templateContent다
    @PostMapping("/api/templates/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTemplate(@PathVariable Long templateSeq,
                               @ModelAttribute TemplateUpdateRequest req) {
        templateCommandService.updateTemplate(templateSeq, req);
    }
}
