package com.flightbooker.flightbookerms.repository;

import com.flightbooker.flightbookerms.model.FlightApiModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FlightModelRepository {
    private final List<FlightApiModel> flightApiModels = new ArrayList<>();

    public void cacheModels(List<FlightApiModel> models) {
        flightApiModels.addAll(models);
    }

    public Optional<FlightApiModel> getFlightModel(UUID uuid) {
        return flightApiModels.stream().filter(flightApiModel -> flightApiModel.getUuid().equals(uuid)).findFirst();
    }
}
