package com.allways.domain.user.dto;

import com.allways.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userSeq;
    private String  userId;
    private String email;
    private String nickname;

    public static UserDto toDto(Optional<User> userOptional) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getUserSeq(), user.getUserId(), user.getEmail(), user.getNickname());
        } else {
            // Handle the case when the Optional is empty, for example, return null or throw an exception.
            return null; // Or throw new SomeException("User not present");
        }
    }
}