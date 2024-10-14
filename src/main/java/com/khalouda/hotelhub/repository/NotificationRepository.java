package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {
}
