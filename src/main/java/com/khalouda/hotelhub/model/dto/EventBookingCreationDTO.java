package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class EventBookingCreationDTO {
    private LocalDateTime eventDateTime;
    private Long durationInMinutes;
    private Long eventSpaceId;
}
