package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.entity.Notification;
import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;
    @Autowired
    private UserService userService;

    public void createNotification(String content, User user) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setIsRead(Boolean.FALSE);
        notification.setUserId(user);

        repository.save(notification);
    }

}
