package com.khalouda.hotelhub.model.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "staffResponseDTOBuilder")
public class StaffResponseDTO extends UserResponseDTO {
    private String position;
    private BigDecimal salary;
    private String department;
}
