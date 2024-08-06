package com.projects.authdemo.Model;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.projects.authdemo.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name="session")
public class Session extends BaseModel {
    private String token;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)//will save the enum as int instead string
    //to save memory
    private Status status;


}
