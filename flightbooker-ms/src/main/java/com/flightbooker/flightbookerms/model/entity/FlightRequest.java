package com.flightbooker.flightbookerms.model.entity;

import com.flightbooker.flightbookerms.model.FlightApiModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "flight_requests")
@Data
public class FlightRequest {
    @Id
    @GeneratedValue
    private Integer id;
    private Location departure;
    private Location destination;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate departureTime;
    private String route;
    @Enumerated(EnumType.STRING)
    private FlightRequestStatus status;
    @OneToOne
    private User requestedBy;

    public FlightRequest() {}

    public FlightRequest(FlightApiModel apiModel) {
        departure = apiModel.getDeparture();
        destination = apiModel.getDestination();
        departureTime = apiModel.getDepartureTime();
        route = apiModel.getRoute();
        status = FlightRequestStatus.PENDING;
    }
}
