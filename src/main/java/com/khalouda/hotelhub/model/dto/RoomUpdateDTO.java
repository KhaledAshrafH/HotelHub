package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RoomUpdateDTO {
    private String roomNumber;
    private String hotelName;
    private int capacity;
    private BigDecimal pricePerNight;
    private RoomStatus status;
}
