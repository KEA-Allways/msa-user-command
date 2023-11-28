package com.allways.domain.blog.controller;

import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.service.BlogCommandService;
import com.allways.common.factory.blog.BlogCreateRequestFactory;
import com.allways.common.factory.blog.BlogUpdateRequestFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BlogCommandControllerTest {
    private MockMvc mockMvc;
    @InjectMocks private BlogCommandController blogCommandController;
    @Mock private BlogCommandService blogCommandService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogCommandController).build();
    }

    @Test
    void createBlogTest() throws Exception {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory.createBlogCreateRequest();

        // When, Then
        mockMvc.perform(post("/api/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("userSeq", 1L) // userSeq 헤더 추가
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated());

        verify(blogCommandService, times(1)).createBlog(createRequest, 1L);
    }

    @Test
    void updateBlogTest() throws Exception {
        // Given
        Long blogSeq = 1L;
        BlogUpdateRequest updateRequest = BlogUpdateRequestFactory.createBlogUpdateRequest();

        // When, Then
        mockMvc.perform(put("/api/blog/{blogSeq}", blogSeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(blogCommandService, times(1)).updateBlog(blogSeq, updateRequest);
    }

    @Test
    void deleteBlogTest() throws Exception {
        // Given
        Long blogSeq = 1L;

        // When, Then
        mockMvc.perform(delete("/api/blog/{blogSeq}", blogSeq))
                .andExpect(status().isOk());

        verify(blogCommandService, times(1)).deleteBlog(blogSeq);
    }

    // 객체를 JSON 문자열로 변환하는 유틸리티 메서드
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
