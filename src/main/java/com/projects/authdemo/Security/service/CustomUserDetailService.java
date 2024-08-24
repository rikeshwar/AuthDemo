package com.projects.authdemo.Security.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.UserRepository;
import com.projects.authdemo.Security.DTO.CustomUserDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.getUserByEmail(username);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("there is no user with "+ username);
        CustomUserDetail customUserDetail=new CustomUserDetail(userOptional.get());

        return customUserDetail;
    }
}
