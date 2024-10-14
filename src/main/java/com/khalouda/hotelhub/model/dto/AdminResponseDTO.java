package com.khalouda.hotelhub.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "adminResponseDTOBuilder")
public class AdminResponseDTO extends UserResponseDTO{
    private String adminLevel;
    private String permissions;
}
