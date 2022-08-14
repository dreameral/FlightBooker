package com.flightbooker.flightbookerms.repository;

import com.flightbooker.flightbookerms.model.entity.Location;
import com.flightbooker.flightbookerms.model.entity.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Integer> {

    List<Segment> findByDeparture(Location departure);

}
