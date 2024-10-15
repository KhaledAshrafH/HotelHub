package com.khalouda.hotelhub.model.mapper;


import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.Admin;
import com.khalouda.hotelhub.model.entity.Guest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface GuestMapper {
    GuestResponseDTO toResponseDTO(Guest guest);
    List<GuestResponseDTO> toResponseDTOs(List<Guest> guests);
    GuestUpdateDTO toUpdateDTO(Guest guest);

    Guest toEntity(GuestUpdateDTO guestUpdateDTO);
    Guest toEntity(GuestRegistrationRequestDTO registrationRequestDTO);
}
