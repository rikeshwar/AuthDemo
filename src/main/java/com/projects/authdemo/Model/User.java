package com.projects.authdemo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projects.authdemo.Security.DTO.CustomUserDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = CustomUserDetail.class, name = "customUserDetail")
//})
@Setter
@Getter
@Entity
@JsonDeserialize
@Table(name="user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;//one user could be many role so role should be collect
    //so java was trying to say @ManyToMany attribute should be of container type

//    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Session> sessions;

    public User()
    {
        this.role=new ArrayList<>();
    }
    //this mostly won't be a problem for Json serialization and deserialization
    //but sometimes can affect system performance due to unnecessory overhead of
    //initializzing the role



    public User(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password=password;
        this.role=new ArrayList<>();
    }
}
