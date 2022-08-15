package com.flightbooker.flightbookerms.controller;

import com.flightbooker.flightbookerms.model.entity.Notification;
import com.flightbooker.flightbookerms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping("/notifications")
    public List<Notification> myNotifications() {
        return service.getNotifications();
    }

    @PostMapping("/markNotificationAsRead/{id}")
    public void markAsRead(@PathVariable("id") Integer id) {
        service.markAsRead(id);
    }

}
