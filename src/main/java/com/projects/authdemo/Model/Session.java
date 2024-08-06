package com.projects.authdemo.Model;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.projects.authdemo.Enum.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private Status status;


}
