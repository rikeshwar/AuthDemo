package com.projects.authdemo.DTO;

import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long user_id;
    private String user_name;
    //private String token;

    public static UserResponseDto from(User user)
    {
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setUser_id(user.getId());
        userResponseDto.setUser_name(user.getName());
        //userResponseDto.setToken(session.getToken());
        return userResponseDto;
    }

}
