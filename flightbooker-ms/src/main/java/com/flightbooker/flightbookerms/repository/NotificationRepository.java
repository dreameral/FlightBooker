package com.flightbooker.flightbookerms.repository;

import com.flightbooker.flightbookerms.model.entity.Notification;
import com.flightbooker.flightbookerms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserAndIsRead(User user, Boolean isRead);

}
