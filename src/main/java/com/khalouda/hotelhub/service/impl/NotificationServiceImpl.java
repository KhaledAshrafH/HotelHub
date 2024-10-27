package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.model.dto.NotificationCreationDTO;
import com.khalouda.hotelhub.model.dto.NotificationResponseDTO;
import com.khalouda.hotelhub.model.entity.Notification;
import com.khalouda.hotelhub.model.entity.User;
import com.khalouda.hotelhub.model.enums.NotificationStatus;
import com.khalouda.hotelhub.model.mapper.NotificationMapper;
import com.khalouda.hotelhub.repository.NotificationRepository;
import com.khalouda.hotelhub.repository.UserRepository;
import com.khalouda.hotelhub.service.EmailNotificationService;
import com.khalouda.hotelhub.service.NotificationService;
import com.khalouda.hotelhub.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final EmailNotificationService emailNotificationService;
    private final UserRepository userRepository;

    @Override
    public NotificationResponseDTO sendNotification(NotificationCreationDTO notificationCreationDTO) {
        Notification notification = Notification.builder()
                .message(notificationCreationDTO.getMessage())
                .status(NotificationStatus.UNREAD)
                .type(notificationCreationDTO.getType())
                .build();

        notification.setUser(userRepository.findById(notificationCreationDTO.getUserId()).orElseThrow(()-> new NoSuchElementException("User not found")));


        Notification savedNotification = notificationRepository.save(notification);
        emailNotificationService.sendNotificationEmail(savedNotification);
        return notificationMapper.toResponseDTO(savedNotification);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsForUser() {
        User user = UtilityService.getCurrentUser();
        return notificationMapper.toResponseDTOs(notificationRepository.findByUserAndStatusNot(user,NotificationStatus.DELETED));
    }

    @Override
    public void markNotificationAsRead(Long notificationId) {
        User user = UtilityService.getCurrentUser();
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        if(user.getId().equals(notification.getUser().getId())) {
            notification.setStatus(NotificationStatus.READ);
            notificationRepository.save(notification);
        }
        else
            throw new RuntimeException("You are not allowed to mark this notification");

    }

    @Override
    public void deleteNotification(Long notificationId) {
        User user = UtilityService.getCurrentUser();
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        if(user.getId().equals(notification.getUser().getId())) {
            notification.setStatus(NotificationStatus.DELETED);
            notificationRepository.save(notification);
        }
        else
            throw new RuntimeException("You are not allowed to mark this notification");

    }


}
