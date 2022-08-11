package com.flightbooker.flightbookerms.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flightrequest")
@Data
public class FlightRequest {
    @Id
    @GeneratedValue
    private Integer id;
    private Location departure;
    private Location destination;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @OneToMany
    private List<Route> routes;
    @Enumerated(EnumType.STRING)
    private FlightRequestStatus status;
    @OneToOne
    private User requestedBy;
}
