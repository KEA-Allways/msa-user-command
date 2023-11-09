package com.allways.domain.user.dto;

import com.allways.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userSeq;
    private String  userId;
    private String email;
    private String nickname;

    public static UserDto toDto(User user){
        return new UserDto(user.getUserSeq(),user.getUserId(),user.getEmail(),user.getNickname());
    }
}
