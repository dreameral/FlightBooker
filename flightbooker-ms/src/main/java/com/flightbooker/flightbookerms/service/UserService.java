package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
