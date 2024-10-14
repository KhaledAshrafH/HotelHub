package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.RoomAvailabilityCreationDTO;
import com.khalouda.hotelhub.model.dto.RoomAvailabilityResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomAvailabilityUpdateDTO;
import com.khalouda.hotelhub.model.entity.RoomAvailability;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomAvailabilityMapper {
    RoomAvailabilityResponseDTO toResponseDTO(RoomAvailability roomAvailability);
    List<RoomAvailabilityResponseDTO> toResponseDTOs(List<RoomAvailability> roomAvailabilities);
    RoomAvailabilityCreationDTO toCreationDTO(RoomAvailability roomAvailability);
    RoomAvailabilityUpdateDTO toUpdateDTO(RoomAvailability roomAvailability);

    RoomAvailability toEntity(RoomAvailabilityCreationDTO roomAvailabilityCreationDTO);
    RoomAvailability toEntity(RoomAvailabilityUpdateDTO roomAvailabilityUpdateDTO);
}