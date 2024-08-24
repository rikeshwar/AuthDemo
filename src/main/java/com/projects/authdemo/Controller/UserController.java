package com.projects.authdemo.Controller;

import com.projects.authdemo.DTO.*;
import com.projects.authdemo.Exception.InvalidCredentialException;
import com.projects.authdemo.Exception.InvalidRequestException;
import com.projects.authdemo.Exception.UserAllReadyExistException;
import com.projects.authdemo.Exception.UserNotFoundException;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
    @PostMapping("/validate")
    public ValidateResponseDto validate(//@RequestHeader("Auth_Token") String token,
                                        //@RequestHeader("user_id") Long id
                                        @RequestBody ValidateRequestDto validateRequestDto)
    {
        //validate user
        return  userService.validate(validateRequestDto.getUser_id(),validateRequestDto.getToken());
    }




    @PostMapping("/role")
    public ResponseEntity<UserResponseDto> assignRole(@RequestParam(name ="role") String role_name,
                                      @RequestParam(name = "user_id") String id) throws UserNotFoundException
    {
        User user=userService.assignRole(Long.valueOf(id),role_name);

        return new ResponseEntity<>(UserResponseDto.from(user),HttpStatus.OK);

    }


    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) throws InvalidRequestException, UserAllReadyExistException
    {
        if(userRequestDto.getName()==null||userRequestDto.getEmail()==null||userRequestDto.getPassword()==null)
            throw new InvalidRequestException("required information is missing");

        UserResponseDto userResponseDto=userService.SignInAndCreateUser(userRequestDto.getName(),userRequestDto.getEmail(),userRequestDto.getPassword());

        ResponseEntity<UserResponseDto> response=new ResponseEntity<>(
                userResponseDto,
                HttpStatus.OK
        );
        return response;
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> logInAndGetToken(@RequestParam("user_id") String email,
//                                                                        @RequestParam("password") String password) throws InvalidCredentialException
//    {
//        UserLogInResponseDto userLogInResponseDto =userService.logInAndGetToken(email,password);
//
//
//        return new ResponseEntity<>(userLogInResponseDto.getName(), userLogInResponseDto.getMultiValueMap(),HttpStatus.OK);
//    }
}
