package com.projects.authdemo.Security.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projects.authdemo.Model.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;
    public CustomGrantedAuthority(Role role)
    {
        this.role=role;
    }
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
