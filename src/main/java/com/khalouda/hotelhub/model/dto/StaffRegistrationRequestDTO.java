package com.khalouda.hotelhub.model.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "staffRegistrationRequestDTOBuilder")
public class StaffRegistrationRequestDTO extends RegistrationRequestDTO{
    private String position;
    private BigDecimal salary;
    private String department;
    private Long hotelId;
}
