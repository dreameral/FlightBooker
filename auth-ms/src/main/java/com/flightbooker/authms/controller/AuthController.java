package com.flightbooker.authms.controller;

import com.flightbooker.authms.model.entity.User;
import com.flightbooker.authms.model.entity.UserRole;
import com.flightbooker.authms.repository.UserRepository;
import com.flightbooker.authms.security.jwt.JwtUtils;
import com.flightbooker.authms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (!userService.verifyCredentials(username, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String jwt = jwtUtils.generateJwtToken(username, password);

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            // TODO throw bad request exception
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(UserRole.ADMIN);

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("ok", "Registered successfully");
        return ResponseEntity.ok(response);
    }

}
