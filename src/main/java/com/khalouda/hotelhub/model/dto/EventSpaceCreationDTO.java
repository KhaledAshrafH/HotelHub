package com.khalouda.hotelhub.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class EventSpaceCreationDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer capacity;
}
