package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.entity.Amenity;
import com.khalouda.hotelhub.model.enums.AmenityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AmenityResponseDTO {
    private Long amenityId;
    private String name;
    private String description;
    private AmenityType amenityType;
    private String hotelName;
    private String roomNumber;
}
