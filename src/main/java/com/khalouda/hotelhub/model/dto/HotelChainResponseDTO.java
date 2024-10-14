package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class HotelChainResponseDTO {
    private Long chainId;
    private String name;
    private String description;
    private String headquartersLocation;
}
