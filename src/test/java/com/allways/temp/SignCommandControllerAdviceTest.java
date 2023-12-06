package com.allways.temp;

import com.allways.common.advice.ExceptionAdvice;
import com.allways.domain.user.controller.SignCommandController;
import com.allways.domain.user.dto.SignInRequest;
import com.allways.domain.user.dto.SignUpRequest;
import com.allways.domain.user.exception.AuthenticationEntryPointException;
import com.allways.domain.user.exception.LoginFailureException;
import com.allways.domain.user.exception.UserEmailAlreadyExistsException;
import com.allways.domain.user.exception.UserNicknameAlreadyExistsException;
import com.allways.domain.user.service.SignService;
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

import static com.allways.common.factory.user.SignInRequestFactory.createSignInRequest;
import static com.allways.common.factory.user.SignUpRequestFactory.createSignUpRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SignCommandControllerAdviceTest {
    @InjectMocks
	SignCommandController signCommandController;
    @Mock
    SignService signService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(signCommandController).setControllerAdvice(new ExceptionAdvice()).build();
    }
    @Test
    void signInLoginFailureExceptionTest() throws Exception{
        SignInRequest req = createSignInRequest("email@email.com", "123456a!");
        given(signService.signIn(any())).willThrow(LoginFailureException.class);

        mockMvc.perform(
                post("/api/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void signInMethodArgumentNotValidExceptionTest() throws Exception{
        // 비밀번호가 숫자로만 이루어져서 에러 발생
        SignInRequest req = createSignInRequest("email","1234567");

        mockMvc.perform(
                post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpMemberEmailAlreadyExistsExceptionTest() throws Exception {
        // given
        SignUpRequest req = createSignUpRequest("userId","123456a!","nickname" , "email@email.com", "test.jpg");
        doThrow(UserEmailAlreadyExistsException.class).when(signService).signUp(any());

        // when, then
        mockMvc.perform(
                        post("/api/auth/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isConflict());
    }

    @Test
    void signUpMemberNicknameAlreadyExistsExceptionTest() throws Exception{

        SignUpRequest req = createSignUpRequest("userId","123456a!","nickname" , "email@email.com", "test.jpg");
        doThrow(UserNicknameAlreadyExistsException.class).when(signService).signUp(any());

        mockMvc.perform(
                post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isConflict());
    }



    @Test
    void signUpMethodArgumentNotValidExceptionTest() throws Exception{
        SignUpRequest req =createSignUpRequest("","","","","");
        mockMvc.perform(
                post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());

    }

    // @Test
    // void refreshTokenAuthenticationEntryPointException()throws Exception {
    //     given(signService.createNewAccessToken(anyString())).willThrow(AuthenticationEntryPointException.class);
    //
    //     mockMvc.perform(
    //             post("/api/auth/sign-in")
    //                     .header("Authorization","refreshToken")
    //     ).andExpect(status().isUnauthorized())
    //             .andExpect(jsonPath("$.code").value(-1001));
    // }
    //
    // @Test
    // void refreshTokenMissingRequestHeaderException () throws Exception {
    //     mockMvc.perform(
    //             post("/api/refresh-token")
    //       ).andExpect(status().isBadRequest())
    //             .andExpect(jsonPath("$.code").value(-1009));
    // }


}
