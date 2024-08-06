package com.projects.authdemo.Controller;

import com.projects.authdemo.DTO.UserRequestDto;
import com.projects.authdemo.DTO.UserResponseDto;
import com.projects.authdemo.Exception.InvalidRequestException;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) throws InvalidRequestException
    {
        if(userRequestDto.getName()==null||userRequestDto.getEmail()==null)
            throw new InvalidRequestException("required information is missing");

        User user=userService.createUser(userRequestDto.getName(),userRequestDto.getEmail());

        return new ResponseEntity<>(UserResponseDto.from(user), HttpStatus.OK);
    }
}
