package com.khalouda.hotelhub.model.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "staffUpdateDTOBuilder")
public class StaffUpdateDTO extends UserUpdateDTO {
    private String position;
    private BigDecimal salary;
    private String department;
}
