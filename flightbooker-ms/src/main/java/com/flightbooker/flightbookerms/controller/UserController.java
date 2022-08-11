package com.flightbooker.flightbookerms.controller;

import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

}
