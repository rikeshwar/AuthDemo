package com.projects.authdemo.Service;


import com.projects.authdemo.Config.UserConfig;
import com.projects.authdemo.DTO.UserResponseDto;
import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConfig userConfig;
    @Autowired
    SessionService sessionService;


    public UserResponseDto createUser(String name, String email, String password)
    {
        String enCryptedPassword=userConfig.getPasswordEncoder().encode(password);


        User user= userRepository.save(new User(name,email,enCryptedPassword));
        Session session=sessionService.createSession(user);
        UserResponseDto userResponseDto=UserResponseDto.from(user,session);
        return userResponseDto;


    }

}
