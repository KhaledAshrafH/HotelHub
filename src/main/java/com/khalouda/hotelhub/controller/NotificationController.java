package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.NotificationCreationDTO;
import com.khalouda.hotelhub.model.dto.NotificationResponseDTO;
import com.khalouda.hotelhub.model.entity.Notification;
import com.khalouda.hotelhub.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@RequestBody NotificationCreationDTO notificationCreateDTO) {
        return ResponseEntity.ok(notificationService.sendNotification(notificationCreateDTO));
    }

}
