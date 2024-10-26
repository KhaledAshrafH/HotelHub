package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.entity.RoomType;
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
public class RoomResponseDTO {
    private String hotelName;
    private Long roomId;
    private String roomNumber;
    private int capacity;
    private BigDecimal pricePerNight;
    private RoomStatus status;
    private RoomTypeResponseDTO type;
}
