package com.projects.authdemo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Role extends BaseModel {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    List<User> users;


}
