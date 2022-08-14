package com.flightbooker.flightbookerms.repository;

import com.flightbooker.flightbookerms.model.entity.FlightRequest;
import com.flightbooker.flightbookerms.model.entity.FlightRequestStatus;
import com.flightbooker.flightbookerms.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRequestRepository extends PagingAndSortingRepository<FlightRequest, Integer> {

    Page<FlightRequest> findByStatus(FlightRequestStatus status, Pageable pageable);

    int countByRequestedBy(User user);

}
