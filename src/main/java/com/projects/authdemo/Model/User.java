package com.projects.authdemo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity

@Table(name="user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Role> role;//one user could be many role so role should be collect
    //so java was trying to say @ManyToMany attribute should be of container type

//    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Session> sessions;

    public User()
    {
        this.role=new ArrayList<>();
    }

    public User(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password=password;
        this.role=new ArrayList<>();
    }
}
