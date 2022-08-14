package com.flightbooker.flightbookerms.repository;

import com.flightbooker.flightbookerms.model.entity.FlightRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRequestRepository extends JpaRepository<FlightRequest, Integer> {
}
