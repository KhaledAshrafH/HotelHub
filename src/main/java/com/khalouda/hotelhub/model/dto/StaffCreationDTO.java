package com.khalouda.hotelhub.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "staffCreationDTOBuilder")
public class StaffCreationDTO  extends UserCreationDTO{
    private String position;
    private double salary;
    private String department;
}
