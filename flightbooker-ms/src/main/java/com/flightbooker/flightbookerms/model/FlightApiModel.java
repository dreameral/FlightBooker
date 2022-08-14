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
    private Location departure;
    private Location destination;
    private LocalDate departureTime;
    private String route;

    public FlightApiModel() {

    }

    public FlightApiModel(FlightRequest flightRequest) {
        this.departure = flightRequest.getDeparture();
        this.destination = flightRequest.getDestination();
        this.departureTime = flightRequest.getDepartureTime();
        this.route = flightRequest.getRoute();
    }
}
