package com.projects.authdemo.DTO;

import com.projects.authdemo.Enum.TokenStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResponseDto {
    private boolean IsUserExist;
    private TokenStatus tokenStatus;
}
