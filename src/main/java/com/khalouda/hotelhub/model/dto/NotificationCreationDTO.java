package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.NotificationStatus;
import com.khalouda.hotelhub.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationCreationDTO {
    private Long userId;
    private String message;
    private NotificationType type;
    private NotificationStatus status;
}
