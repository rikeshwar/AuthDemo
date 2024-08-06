package com.projects.authdemo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="user")
public class User extends BaseModel{
    private String name;
    private String email;
    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    private List<Session> sessions;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
