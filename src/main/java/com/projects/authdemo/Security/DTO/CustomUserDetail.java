package com.projects.authdemo.Security.DTO;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projects.authdemo.Model.Role;
import com.projects.authdemo.Model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//UserDetail interface provided by the spring security has three method and other
//default method which are optional to implement


//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = CustomUserDetail.class, name = "customUserDetail")
//})
//    @JsonProperty("username") String username;
//    @JsonProperty("password") String password;
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
public class CustomUserDetail implements UserDetails {

    private User user;


    public CustomUserDetail(User user)
    {
        this.user=user;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<CustomGrantedAuthority> customGrantedAuthorities=new ArrayList<>();
        for(Role role:user.getRole())
        {
            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        return customGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
