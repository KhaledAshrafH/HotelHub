package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.RoomsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeUpdateDTO {
    private RoomsType typeName;
    private String description;
    private int maxOccupancy;
}
