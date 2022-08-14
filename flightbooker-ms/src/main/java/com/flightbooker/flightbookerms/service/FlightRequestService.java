package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.FlightApiModel;
import com.flightbooker.flightbookerms.model.entity.Location;
import com.flightbooker.flightbookerms.repository.FlightModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightRequestService {

    @Autowired
    private SegmentService segmentService;
    @Autowired
    private FlightModelRepository flightModelRepository;

    public List<FlightApiModel> getAvailableFlights(Location departure, Location destination, LocalDate departureDate) {
        //TODO availableRoutes should take into account the departure time
        List<String> availableRoutes = segmentService.getAvailableRoutes(departure, destination);

        List<FlightApiModel> flightApiModels = availableRoutes.stream()
                .map(route -> FlightApiModel.builder()
                        .uuid(UUID.randomUUID())
                        .departure(departure)
                        .destination(destination)
                        .route(route)
                        .departureTime(departureDate)
                        .build())
                .collect(Collectors.toList());
        flightModelRepository.cacheModels(flightApiModels);

        return flightApiModels;
    }

    public Optional<FlightApiModel> getFlightApiModel(UUID uuid) {
        return flightModelRepository.getFlightModel(uuid);
    }

}
