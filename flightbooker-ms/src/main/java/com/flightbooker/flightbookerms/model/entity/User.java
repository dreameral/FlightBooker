package com.flightbooker.flightbookerms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;
}
