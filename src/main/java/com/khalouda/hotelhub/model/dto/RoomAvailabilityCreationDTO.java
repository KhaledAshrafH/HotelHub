package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAvailabilityCreationDTO {
    private Long roomId;
    private java.sql.Date availableDate;
    private int availableCount;
}
