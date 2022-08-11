package com.flightbooker.flightbookerms.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private UserRole role;
}
