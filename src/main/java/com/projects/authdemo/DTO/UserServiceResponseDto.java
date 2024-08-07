package com.projects.authdemo.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.net.http.HttpHeaders;


@Setter
@Getter
public class UserServiceResponseDto {
    private Long userId;
    private String name;
    private MultiValueMap<String,String> multiValueMap;
}
