package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.entity.Location;
import com.flightbooker.flightbookerms.model.entity.Segment;
import com.flightbooker.flightbookerms.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SegmentService {

    @Autowired
    private SegmentRepository repository;

    public List<String> getAvailableRoutes(Location departure, Location destination) {
        List<Segment> segments = repository.findAll();
        List<Route> routes = segments.stream().filter(segment -> segment.getDeparture() == departure)
                .map(Route::new).toList();

        List<Route> completedRoutes = new ArrayList<>();
        for (Route route : routes) {
            completeRoute(completedRoutes, route, segments, destination);
        }

        return completedRoutes.stream().map(Object::toString).collect(Collectors.toList());
    }

    private void completeRoute(List<Route> completedRoutes, Route route, List<Segment> segments, Location destination) {
        Optional<Location> routeDestination = route.getDestination();

        if (routeDestination.isEmpty() || routeDestination.get() == destination) {
            // it means that route is completed
            completedRoutes.add(route);
            return;
        }
        Location nextDeparture = routeDestination.get();

        boolean firstIteration = true;
        for (Segment segment : segments) {
            if (segment.getDeparture() == nextDeparture && !route.isLocationVisited(segment.getDeparture())) {
                if (firstIteration) {
                    route.addSegment(segment);
                    completeRoute(completedRoutes, route, segments, destination);
                } else {
                    Route newRoute = new Route(route, segment);
                    completeRoute(completedRoutes, newRoute, segments, destination);
                }
            }
            firstIteration = false;
        }
    }

    private static class Route {
        private final List<Segment> segments;

        public Route(Route route, Segment next) {
            segments = new ArrayList<>();
            segments.addAll(route.getSegments());
            segments.add(next);
        }

        public Route(Segment start) {
            segments = new ArrayList<>();
            segments.add(start);
        }

        public void addSegment(Segment segment) {
            segments.add(segment);
        }

        public Optional<Location> getDestination() {
            return segments.size() > 0 ? Optional.of(segments.get(segments.size() - 1).getDestination()) : Optional.empty();
        }

        public boolean isLocationVisited(Location location) {
            return segments.stream().anyMatch(segment -> segment.getDeparture() == location);
        }

        public List<Segment> getSegments() {
            return segments;
        }

        @Override
        public String toString() {
            return segments.stream()
                    .map(segment ->
                            segment.getDeparture().toString() + "-" + segment.getDestination().toString() + "(" + segment.getAirline().toString() + ")" )
                    .collect(Collectors.joining(","));
        }
    }

}
