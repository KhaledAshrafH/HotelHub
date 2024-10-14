package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeUpdateDTO {
    private String typeName;
    private String description;
    private int maxOccupancy;
}
