package com.khalouda.hotelhub.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "guestResponseDTOBuilder")
public class GuestResponseDTO extends UserResponseDTO{
    private String guestToken;
    private String preferences;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
