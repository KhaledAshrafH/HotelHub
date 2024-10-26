package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Notification;
import com.khalouda.hotelhub.model.entity.User;
import com.khalouda.hotelhub.model.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {
List<Notification> findByUserAndStatusNot(User user, NotificationStatus status);
}
