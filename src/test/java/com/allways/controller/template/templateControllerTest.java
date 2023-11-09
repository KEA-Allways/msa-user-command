package com.allways.controller.template;

import com.allways.domain.template.controller.TemplateCommandController;
import com.allways.domain.template.exception.TemplateNotFoundException;
import com.allways.domain.template.service.TemplateCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class templateControllerTest {

    @InjectMocks
    TemplateCommandController templateController;
    @Mock
    TemplateCommandService templateCommandService;
    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(templateController).build();
    }

    @Test
    void readOneTest() throws Exception {
        // given
        Long userSeq = 1L;
        Long templateSeq = 1L;
        // given(templateCommandService.readOne(templateSeq)).willThrow(TemplateNotFoundException.class);

        // when, then
        mockMvc.perform(
                get("/api/templates/{userSeq}/{templateSeq}", userSeq, templateSeq))
                .andExpect(status().isOk());
        verify(templateCommandService).readOne(userSeq, templateSeq);
    }

    @Test
    void readAllTest() throws Exception {
        // given
        Long userSeq = 1L;

        mockMvc.perform(
                get("/api/templates/{userSeq}", userSeq))
                .andExpect(status().isOk());
        verify(templateCommandService).readAll(userSeq);
    }
}
