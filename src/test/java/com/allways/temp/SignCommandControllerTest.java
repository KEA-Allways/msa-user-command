package com.allways.temp;

import com.allways.domain.user.controller.SignCommandController;
import com.allways.domain.user.dto.SignInResponse;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.service.SignCommandService;
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

import static com.allways.temp.RefreshTokenResponseFactory.createRefreshTokenResponse;
import static com.allways.common.factory.user.SignInRequestFactory.createSignInRequest;

import static com.allways.common.factory.user.SignUpRequestFactory.createSignUpRequest;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SignCommandControllerTest {
    @InjectMocks
	SignCommandController signCommandController;
    @Mock
    SignCommandService signService;
        MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper(); // 1
    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(signCommandController).build();

    }
    @Test
    void signUpTest() throws Exception {
        // given
        SignUpRequest req = createSignUpRequest("userId","123456a!","nickname" , "email@email.com", "test.jpg");

        // when, then
        mockMvc.perform(
                        post("/api/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req))) // 2
                .andExpect(status().isCreated());

        verify(signService).signUp(req);
    }

    @Test
    void signInTest() throws Exception{
        SignInRequest req = createSignInRequest("email@email.com", "123456a!");
        given(signService.signIn(req)).willReturn(new SignInResponse("access","refresh"));

        mockMvc.perform(
                post("/api/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.accessToken").value("access")) // 3
                .andExpect(jsonPath("$.result.data.refreshToken").value("refresh"));

        verify(signService).signIn(req);
    }

    @Test
    void refreshTokenTest() throws Exception {
        given(signService.createNewAccessToken("refreshToken")).willReturn(createRefreshTokenResponse("accessToken"));

        mockMvc.perform(
                post("/api/refresh-token")
                        .header("Authorization","refreshToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.accessToken").value("accessToken"));


    }


}
