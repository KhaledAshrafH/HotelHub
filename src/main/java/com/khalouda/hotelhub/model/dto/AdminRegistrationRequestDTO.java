package com.khalouda.hotelhub.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "adminRegistrationRequestDTOBuilder")
public class AdminRegistrationRequestDTO extends RegistrationRequestDTO {
    private String adminLevel;
    private String permissions;

}