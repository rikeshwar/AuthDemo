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
    List<User> users;//usually we need not to keep the collection at the both the\
    //place role and the user we can keep it any one place
    //and we
    public Role(String name)
    {
        this.name=name;
    }


}
