package com.flightbooker.flightbookerms.service;

import com.flightbooker.flightbookerms.model.entity.Notification;
import com.flightbooker.flightbookerms.model.entity.NotificationType;
import com.flightbooker.flightbookerms.model.entity.User;
import com.flightbooker.flightbookerms.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;
    @Autowired
    private UserService userService;

    public void createNotification(String content, NotificationType notificationType, User user) {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setIsRead(Boolean.FALSE);
        notification.setNotificationType(notificationType);
        notification.setUser(user);

        repository.save(notification);
    }

    public List<Notification> getNotifications() {
        return repository.findByUserAndIsRead(userService.getAuthenticatedUser(), Boolean.FALSE);
    }

    public void markAsRead(Integer id) {
        Optional<Notification> notification = repository.findById(id);
        notification.ifPresent(not -> {
            not.setIsRead(Boolean.TRUE);
            repository.save(not);
        });
    }
}
