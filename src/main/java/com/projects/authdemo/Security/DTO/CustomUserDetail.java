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

@NoArgsConstructor
@JsonDeserialize
public class CustomUserDetail implements UserDetails {

    private String username;
    private String password;
    private List<CustomGrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean accountNonLocked;
    private Long userId;



    public CustomUserDetail(User user)
    {
        this.username=user.getEmail();
        this.password=user.getPassword();
        this.authorities=new ArrayList<>();
        for(Role role:user.getRole())
        {
            authorities.add(new CustomGrantedAuthority(role));
        }
        this.userId=user.getId();
    }
    public Long getUserId()
    {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }
    public boolean isAccountNonExpired() {
        accountNonExpired=true;
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        accountNonLocked=true;
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        credentialsNonExpired=true;
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        enabled=true;
        return enabled;
    }
}
