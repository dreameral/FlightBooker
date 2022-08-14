package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.FlightBookerUserDetails;
import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return repository.findById(id);
    }

    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User getAuthenticatedUser() {
        FlightBookerUserDetails userDetails = (FlightBookerUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return repository.findById(userDetails.getId()).orElse(null);
    }

    public Optional<FlightBookerUserDetails> getUserDetails(String username) {
        Optional<User> user = repository.findByUsername(username);
        return user.map(FlightBookerUserDetails::new);
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
}
