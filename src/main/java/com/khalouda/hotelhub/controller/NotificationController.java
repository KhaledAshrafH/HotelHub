package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.NotificationCreationDTO;
import com.khalouda.hotelhub.model.dto.NotificationResponseDTO;
import com.khalouda.hotelhub.model.entity.Notification;
import com.khalouda.hotelhub.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@RequestBody NotificationCreationDTO notificationCreateDTO) {
        return ResponseEntity.ok(notificationService.sendNotification(notificationCreateDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotificationsForUser() {
        return ResponseEntity.ok(notificationService.getNotificationsForUser());
    }

    @DeleteMapping("{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{notificationId}")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok("The notification has been marked as read");
    }

}
