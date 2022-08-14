package com.flightbooker.flightbookerms.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue
    private Integer id;
    private String content;
    private Boolean isRead;
    @OneToOne
    private User userId;
}
