package com.projects.authdemo.Service;


import com.projects.authdemo.Config.UserConfig;
import com.projects.authdemo.DTO.UserResponseDto;
import com.projects.authdemo.DTO.UserLogInResponseDto;
import com.projects.authdemo.DTO.ValidateResponseDto;
import com.projects.authdemo.Enum.TokenStatus;
import com.projects.authdemo.Exception.InvalidCredentialException;
import com.projects.authdemo.Exception.UserAllReadyExistException;
import com.projects.authdemo.Exception.UserNotFoundException;
import com.projects.authdemo.Model.Role;
import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.RoleRepo;
import com.projects.authdemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import java.net.http.HttpHeaders;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConfig userConfig;
    @Autowired
    SessionService sessionService;
    @Autowired
    RoleRepo roleRepo;


    public UserResponseDto SignInAndCreateUser(String name, String email, String password) throws UserAllReadyExistException
    {
        Optional<User> userOptional=userRepository.getUserByEmail(email);
        if(!userOptional.isEmpty())
            throw new UserAllReadyExistException("user with "+email+" Allready exist");

        String enCryptedPassword=userConfig.getPasswordEncoder().encode(password);
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(enCryptedPassword);
        user=userRepository.save(user);
        UserResponseDto userResponseDto=UserResponseDto.from(user);
        return userResponseDto;




        //userServiceResponseDto.setMultiValueMap(new HttpHeaders());
        //userServiceResponseDto.getMultiValueMap().set("Auth-Token",session.getToken());

    }
    public UserLogInResponseDto logInAndGetToken(String email, String password) throws InvalidCredentialException
    {
        //first verify if the email and password is correct
        Optional<User> userOptional=userRepository.getUserByEmail(email);
        if(userOptional.isEmpty())
            throw new InvalidCredentialException("Bad credential");
        if(!BCrypt.checkpw(password,userOptional.get().getPassword()))
            throw new InvalidCredentialException("you have given a wrong password");
        //now the user is varifoed it time to create session and provide token
       Session session=sessionService.createSession(userOptional.get());
       //now return the userservice response
        UserLogInResponseDto userLogInResponseDto =new UserLogInResponseDto();
        userLogInResponseDto.setUserId(userOptional.get().getId());
        userLogInResponseDto.setName(userOptional.get().getName());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("User_Id",userOptional.get().getId().toString());
        httpHeaders.add("Auth-Token",session.getToken());

        userLogInResponseDto.setMultiValueMap(httpHeaders);
        return userLogInResponseDto;



    }
    public User assignRole(Long id,String role_name) throws UserNotFoundException
    {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty())
         throw new UserNotFoundException("there is no user with id "+id);
        if(userOptional.get().getRole().isEmpty())
            userOptional.get().setRole(new ArrayList<>());
        Optional<Role> roleOptional=roleRepo.findByName(role_name);
        if(roleOptional.isEmpty()) {

            roleOptional=Optional.of(roleRepo.save(new Role(role_name)));
        }
        userOptional.get().getRole().add(roleOptional.get());//get the list of existing
        //role and then add new role into that.

        return userRepository.save(userOptional.get());


    }
    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }
    public Optional<User> getUserById(Long id)
    {
        return userRepository.findById(id);
    }
    public ValidateResponseDto validate(Long user_id,String token)
    {
        Optional<User> userOptional=getUserById(user_id);
        if(userOptional.isEmpty())
            return new ValidateResponseDto(false, TokenStatus.INVALID);
        List<Session> sessions=sessionService.getSessionByUserId(user_id);
        //sessions.forEach(token->);
        for(Session session:sessions)
        {
            if(session.getToken().equals(token))
                return new ValidateResponseDto(true,TokenStatus.VALID);
        }
        return new ValidateResponseDto(true,TokenStatus.INVALID);
    }

}
