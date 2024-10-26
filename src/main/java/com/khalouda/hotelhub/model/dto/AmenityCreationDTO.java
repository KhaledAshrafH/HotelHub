package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.AmenityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AmenityCreationDTO {
    private String amenityName;
    private String description;
    private AmenityType amenityType;
}
