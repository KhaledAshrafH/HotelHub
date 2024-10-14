package com.khalouda.hotelhub.model.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "adminUpdateDTOBuilder")
public class AdminUpdateDTO extends UserUpdateDTO{
    private Long hotelId;
    private String description;
}
