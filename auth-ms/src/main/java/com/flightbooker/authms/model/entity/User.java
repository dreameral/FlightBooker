package com.flightbooker.authms.model.entity;

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
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
