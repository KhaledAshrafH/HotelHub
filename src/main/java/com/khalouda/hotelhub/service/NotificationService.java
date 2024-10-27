package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.NotificationCreationDTO;
import com.khalouda.hotelhub.model.dto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {
    NotificationResponseDTO sendNotification(NotificationCreationDTO notification);
    List<NotificationResponseDTO> getNotificationsForUser();
    void markNotificationAsRead(Long notificationId);
    void deleteNotification(Long notificationId);
}
