package com.projects.authdemo.DTO;

import com.projects.authdemo.Model.Role;
import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long user_id;
    private String user_name;
    private Optional<Role> roleOptional;
    //private String token;

    public static UserResponseDto from(User user)
    {
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setUser_id(user.getId());
        userResponseDto.setUser_name(user.getName());
        //userResponseDto.setToken(session.getToken());
        if(!user.getRole().isEmpty())
            userResponseDto.setRoleOptional(Optional.of(user.getRole().get(0)));
        return userResponseDto;
    }

}
