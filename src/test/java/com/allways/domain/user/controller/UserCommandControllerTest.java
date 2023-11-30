package com.allways.domain.user.controller;

import com.allways.domain.user.dto.UserUpdateRequest;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.service.UserService;
import com.allways.common.factory.user.UserFactory;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserCommandControllerTest {
    private MockMvc mockMvc;
    @InjectMocks private UserCommandController userCommandController;
    @Mock private UserService userService;

    @BeforeEach
    void beforeEach() {
        // MockMvc를 설정하여 컨트롤러를 테스트할 수 있는 환경을 구성합니다.
        mockMvc = MockMvcBuilders.standaloneSetup(userCommandController).build();
    }

    @Test
    void updateUserTest() throws Exception {
        // Given
        Long userSeq = 1L;

        // UserFactory를 사용하여 가짜 User 객체를 생성합니다.
        User user = UserFactory.createUser();

        // UserUpdateRequest 객체를 생성하고, 해당 객체를 JSON 형태로 변환합니다.
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
                user.getPassword(), user.getNickname(),
                user.getEmail(), user.getProfileImgSeq());

        // When
        // userService.updateUser()가 호출되면 아무 작업도 수행하지 않도록 설정합니다.
        doNothing().when(userService).updateUser(any(UserUpdateRequest.class), anyLong());

        // Then
        // 해당 HTTP PUT 요청이 성공적으로 수행되는지 확인합니다.
        mockMvc.perform(put("/api/user")
                        .header("userSeq", String.valueOf(userSeq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userUpdateRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserTest() throws Exception {
        // Given
        Long userSeq = 1L;
        // userService.deleteUser()가 호출되면 아무 작업도 수행하지 않도록 설정합니다.
        doNothing().when(userService).deleteUser(userSeq);

        // When, Then
        // 해당 HTTP DELETE 요청이 성공적으로 수행되는지 확인합니다.
        mockMvc.perform(delete("/api/user")
                        .header("userSeq", String.valueOf(userSeq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
