package com.allways.domain.blog.controller;

import com.allways.domain.blog.dto.BlogCreateRequest;
import com.allways.domain.blog.dto.BlogUpdateRequest;
import com.allways.domain.blog.service.BlogService;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BlogCommandControllerTest {
    private MockMvc mockMvc;
    @InjectMocks private BlogCommandController blogCommandController;
    @Mock private BlogService blogService;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogCommandController).build();
    }

    @Test
    void createBlogTest() throws Exception {
        // Given
        BlogCreateRequest createRequest = BlogCreateRequestFactory
                .createBlogCreateRequest();

        // When, Then
        MvcResult result = mockMvc.perform(post("/api/blog")
                        .header("userSeq", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        verify(blogService).createBlog(createRequest, Long.parseLong(userSeq));
    }

    @Test
    void updateBlogTest() throws Exception {
        // Given
        Long blogSeq = 1L;
        BlogUpdateRequest updateRequest = BlogUpdateRequestFactory
                .createBlogUpdateRequest();

        // When, Then
        mockMvc.perform(put("/api/blog/{blogSeq}", blogSeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(blogService).updateBlog(blogSeq, updateRequest);
    }

    @Test
    void deleteBlogTest() throws Exception {
        // Given
        Long blogSeq = 1L;

        // When, Then
        mockMvc.perform(delete("/api/blog/{blogSeq}", blogSeq))
                .andExpect(status().isOk());

        verify(blogService).deleteBlog(blogSeq);
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
