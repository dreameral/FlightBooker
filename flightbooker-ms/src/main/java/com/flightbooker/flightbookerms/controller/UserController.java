package com.flightbooker.flightbookerms.controller;

import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        service.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        user.setId(id);
        service.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
