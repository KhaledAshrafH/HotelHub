package com.khalouda.hotelhub.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "guestUpdateDTOBuilder")
public class GuestUpdateDTO extends UserUpdateDTO{
    private String preferences;
}
