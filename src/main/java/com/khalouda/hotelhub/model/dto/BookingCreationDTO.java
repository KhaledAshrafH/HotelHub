package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCreationDTO {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private BigDecimal totalAmount;
}
