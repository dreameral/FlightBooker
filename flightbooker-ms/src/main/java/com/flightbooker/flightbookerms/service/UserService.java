package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.FlightBookerUserDetails;
import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<FlightBookerUserDetails> getUserDetails(String username) {
        Optional<User> user = repository.findByUsername(username);
        return user.map(FlightBookerUserDetails::new);
    }

}
