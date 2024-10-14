package com.khalouda.hotelhub.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "guestCreationDTOBuilder")
public class GuestCreationDTO extends UserCreationDTO{
    private String guestToken; // Token for guest identification
    private String preferences; // Guest preferences
    private LocalDateTime checkInDate; // Optional
    private LocalDateTime checkOutDate;
}
