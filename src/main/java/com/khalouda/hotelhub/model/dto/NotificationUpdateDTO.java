package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationUpdateDTO {
    private String message;
    private NotificationStatus status;
}
