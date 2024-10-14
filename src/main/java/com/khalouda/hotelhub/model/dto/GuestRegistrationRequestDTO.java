package com.khalouda.hotelhub.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "guestRegistrationRequestDTOBuilder")
public class GuestRegistrationRequestDTO extends RegistrationRequestDTO {
    private String guestToken;
    private String preferences;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
