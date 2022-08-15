package com.flightbooker.flightbookerms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.flightbooker.flightbookerms.model.entity.FlightRequest;
import com.flightbooker.flightbookerms.model.entity.Location;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightApiModel {
    private UUID uuid;
    private Integer flightId;
    private Location departure;
    private Location destination;
    private LocalDate departureTime;
    private String route;

    public FlightApiModel() {
    }

    public FlightApiModel(UUID uuid, Integer flightId, Location departure, Location destination, LocalDate departureTime, String route) {
        this.uuid = uuid;
        this.flightId = flightId;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.route = route;
    }

    public FlightApiModel(FlightRequest flightRequest) {
        this.flightId = flightRequest.getId();
        this.departure = flightRequest.getDeparture();
        this.destination = flightRequest.getDestination();
        this.departureTime = flightRequest.getDepartureTime();
        this.route = flightRequest.getRoute();
    }
}
