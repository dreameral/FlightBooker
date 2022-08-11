package com.flightbooker.apigateway.service;

import com.flightbooker.apigateway.entity.User;
import com.flightbooker.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyCredentials(String jwtSubject) {
        String[] creds = jwtSubject.split("\\|");
        String username = creds[0];
        String password = creds[0];

        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(user -> user.getPassword().equals(password)).orElse(Boolean.FALSE);
    }

}
