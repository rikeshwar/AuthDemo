package com.projects.authdemo.Service;


import com.projects.authdemo.Config.UserConfig;
import com.projects.authdemo.DTO.UserResponseDto;
import com.projects.authdemo.DTO.UserServiceResponseDto;
import com.projects.authdemo.Exception.UserNotFoundException;
import com.projects.authdemo.Model.Role;
import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.RoleRepo;
import com.projects.authdemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpHeaders;
//import java.net.http.HttpHeaders;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    public UserServiceResponseDto createUser(String name, String email, String password)
    {
        String enCryptedPassword=userConfig.getPasswordEncoder().encode(password);


        User user= userRepository.save(new User(name,email,enCryptedPassword));
        Session session=sessionService.createSession(user);
        UserServiceResponseDto userServiceResponseDto=new UserServiceResponseDto();
        userServiceResponseDto.setUserId(user.getId());
        userServiceResponseDto.setName(user.getName());
        userServiceResponseDto.setMultiValueMap(new HttpHeaders());
        userServiceResponseDto.getMultiValueMap().set("Auth-Token",session.getToken());
        return userServiceResponseDto;
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

}
