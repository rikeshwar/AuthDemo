package com.projects.authdemo.Service;


import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name,String email)
    {
        return userRepository.save(new User(name,email));
    }

}
