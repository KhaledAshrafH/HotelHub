package com.khalouda.hotelhub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountResponseDTO {
    private Long discountId;
    private Long hotelId;
    private String discountCode;
    private String description;
    private double percentage;
}
