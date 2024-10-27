package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class EventBookingResponseDTO {
    private Long eventBookingId;
    private LocalDateTime eventDateTime;
    private Duration duration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<EventAttendeeDTO> attendees;
}
