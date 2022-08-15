package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.FlightApiModel;
import com.flightbooker.flightbookerms.model.entity.*;
import com.flightbooker.flightbookerms.repository.FlightModelRepository;
import com.flightbooker.flightbookerms.repository.FlightRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private FlightRequestRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

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

    public boolean bookFlight(UUID flightModelUid) {
        Optional<FlightApiModel> flightApiModel = flightModelRepository.getFlightModel(flightModelUid);
        if (flightApiModel.isEmpty() || getFlightsRemaining() == 0) {
            return false;
        }
        User authenticatedUser = userService.getAuthenticatedUser();
        FlightRequest flightRequest = new FlightRequest(flightApiModel.get());
        flightRequest.setRequestedBy(authenticatedUser);

        repository.save(flightRequest);
        return true;
    }

    public Optional<FlightApiModel> getFlightApiModel(UUID uuid) {
        return flightModelRepository.getFlightModel(uuid);
    }

    public boolean updateStatus(Integer id, FlightRequestStatus status, String reason) {
        Optional<FlightRequest> flightRequest = repository.findById(id);
        if (flightRequest.isEmpty()) {
            return false;
        }
        flightRequest.get().setStatus(status);
        repository.save(flightRequest.get());

        if (status == FlightRequestStatus.APPROVED && !StringUtils.hasText(reason)) {
            reason = String.format("Your flight request to '%s' has been approved.", flightRequest.get().getRoute());
        }

        notificationService.createNotification(reason,
                status == FlightRequestStatus.APPROVED ? NotificationType.FLIGHT_APPROVED : NotificationType.FLIGHT_REJECTED,
                flightRequest.get().getRequestedBy());

        return true;
    }

    public List<FlightApiModel> getFlightsWithStatus(FlightRequestStatus status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByStatus(status, pageable).stream().map(FlightApiModel::new).collect(Collectors.toList());
    }

    public int getFlightsRemaining() {
        int currentBooked = repository.countByRequestedBy(userService.getAuthenticatedUser());
        return Math.max(20 - currentBooked, 0);
    }
}
