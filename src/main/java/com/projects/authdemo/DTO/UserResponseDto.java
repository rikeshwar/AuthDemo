package com.projects.authdemo.DTO;

import com.projects.authdemo.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class UserResponseDto {
    private Long user_id;
    private String user_name;

    public static UserResponseDto from(User user)
    {
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setUser_id(user.getId());
        userResponseDto.setUser_name(user.getName());
        return userResponseDto;
    }

}
