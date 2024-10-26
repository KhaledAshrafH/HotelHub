package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.RoomStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RoomCreationDTO {
    private Long roomTypeId;
    private String roomNumber;
    private int capacity;
    private BigDecimal pricePerNight;
}
