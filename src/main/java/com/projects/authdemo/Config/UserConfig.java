package com.projects.authdemo.Config;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder()
    {
       return new BCryptPasswordEncoder();
    }




}
