package com.flightbooker.flightbookerms.controller;

import com.flightbooker.flightbookerms.model.FlightApiModel;
import com.flightbooker.flightbookerms.model.PageableResponse;
import com.flightbooker.flightbookerms.model.entity.FlightRequestStatus;
import com.flightbooker.flightbookerms.model.entity.Location;
import com.flightbooker.flightbookerms.service.FlightRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class FlightRequestController {

    @Autowired
    private FlightRequestService service;

    @GetMapping("/myFlights")
    public PageableResponse<FlightApiModel> myFlights(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<FlightApiModel> items = service.getFlightsWithStatus(FlightRequestStatus.APPROVED, page, size);
        PageableResponse<FlightApiModel> response = new PageableResponse<>(items, page, size);
        response.getOtherProperties().put("flightsRemaining", service.getFlightsRemaining());

        return response;
    }

    @GetMapping("/flightsToReview")
    public PageableResponse<FlightApiModel> getFlightsToReview(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<FlightApiModel> items = service.getFlightsWithStatus(FlightRequestStatus.PENDING, page, size);
        return new PageableResponse<>(items, page, size);
    }

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

    @PostMapping("/bookFlight")
    public ResponseEntity<?> bookFlight(@RequestBody Map<String, String> requestBody) {
        String uid = requestBody.get("flightUid");
        UUID flightUid = UUID.fromString(uid);
        boolean success = service.bookFlight(flightUid);

        Map<String, Object> response = new HashMap<>();
        response.put("ok", success);
        response.put("message", success ? "Flight was booked successfully." : "Flight booking failed.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/resolveFlightRequest/{id}")
    public ResponseEntity<?> resolveFlightRequest(@RequestBody Map<String, String> requestBody, @PathVariable(name = "id") Integer id) {
        FlightRequestStatus status = FlightRequestStatus.valueOf(requestBody.get("status"));
        String reason = requestBody.get("reason");
        if (status == FlightRequestStatus.REJECTED && !StringUtils.hasText(reason)) {
            return ResponseEntity.badRequest().body("Specify the reason why you are rejecting this flight request.");
        }

        boolean success = service.updateStatus(id, status, reason);

        Map<String, Object> response = new HashMap<>();
        response.put("ok", success);
        response.put("message", success ? "Flight was booked successfully." : "Flight booking failed.");
        return ResponseEntity.ok(response);
    }
}
