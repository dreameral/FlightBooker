package com.flightbooker.flightbookerms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @JsonIgnore
    @OneToOne
    private User user;
}
