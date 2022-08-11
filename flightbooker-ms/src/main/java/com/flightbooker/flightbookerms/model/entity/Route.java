package com.flightbooker.flightbookerms.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "route")
@Data
public class Route {
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Location departure;
    @Enumerated(EnumType.STRING)
    private Location destination;
    @Enumerated(EnumType.STRING)
    private Airline airline;
}
