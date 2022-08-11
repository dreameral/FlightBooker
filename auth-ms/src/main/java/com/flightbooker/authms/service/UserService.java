package com.flightbooker.authms.service;

import com.flightbooker.authms.model.entity.User;
import com.flightbooker.authms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean verifyCredentials(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(user -> user.getPassword().equals(passwordEncoder.encode(password))).orElse(Boolean.FALSE);
    }

}
