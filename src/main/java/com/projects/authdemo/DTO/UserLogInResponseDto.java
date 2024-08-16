package com.projects.authdemo.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;


@Setter
@Getter
public class UserLogInResponseDto {
    private Long userId;
    private String name;
    private MultiValueMap<String,String> multiValueMap;
}
