package com.flightbooker.flightbookerms.controller;

import com.flightbooker.flightbookerms.model.FlightApiModel;
import com.flightbooker.flightbookerms.model.entity.Location;
import com.flightbooker.flightbookerms.service.FlightRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class FlightRequestController {
    @Autowired
    private FlightRequestService service;

    @GetMapping("/suggestFlights")
    public List<FlightApiModel> suggestFlights(@QueryParam("departure") Location departure,
                                               @QueryParam("destination") Location destination,
                                               @QueryParam("departureTime") String departureTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate departureDate = null;
        try {
            departureDate = LocalDate.parse(departureTime, formatter);
        } catch (DateTimeParseException e) {}

        if (departureDate == null || LocalDate.now().isAfter(departureDate)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Incorrect departure time.");
        }

        return service.getAvailableFlights(departure, destination, departureDate);
    }
}
