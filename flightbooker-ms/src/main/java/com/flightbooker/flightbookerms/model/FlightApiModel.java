package com.flightbooker.flightbookerms.model;

import com.flightbooker.flightbookerms.model.entity.Location;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FlightApiModel {
    private UUID uuid;
    private Location departure;
    private Location destination;
    private LocalDate departureTime;
    private String route;
}
