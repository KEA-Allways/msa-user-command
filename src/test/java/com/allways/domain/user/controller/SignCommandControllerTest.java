package com.allways.domain.user.controller;

import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignInResponse;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.service.SignCommandService;
import com.allways.common.factory.user.SignInRequestFactory;
import com.allways.common.factory.user.SignInResponseFactory;
import com.allways.common.factory.user.SignUpRequestFactory;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SignCommandControllerTest {
    private MockMvc mockMvc;
    @InjectMocks private SignCommandController signCommandController;
    @Mock private SignCommandService signService;

    @BeforeEach
    void BeforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(signCommandController).build();
    }

    @Test
    void signUpTest() throws Exception {
        // Given
        SignUpRequest signUpRequest = SignUpRequestFactory.createSignUpRequest();

        // When
        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signUpRequest)))
                .andExpect(status().isCreated());

        // Then
        verify(signService).signUp(signUpRequest);
    }

    @Test
    void signInTest() throws Exception {
        // Given
        SignInRequest signInRequest = SignInRequestFactory.createSignInRequest();
        SignInResponse signInResponse = SignInResponseFactory.createSignInResponse(
                "accessToken", "refreshToken");
        // 가짜 응답을 생성하기 위한 모의 객체 설정
        given(signService.signIn(signInRequest)).willReturn(signInResponse);

        // When, Then
        mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signInRequest)))
                .andExpect(status().isOk());

        verify(signService).signIn(signInRequest);
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
