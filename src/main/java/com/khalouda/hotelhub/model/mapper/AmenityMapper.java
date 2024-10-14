package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.AmenityCreationDTO;
import com.khalouda.hotelhub.model.dto.AmenityResponseDTO;
import com.khalouda.hotelhub.model.dto.AmenityUpdateDTO;
import com.khalouda.hotelhub.model.entity.Amenity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AmenityMapper {
    AmenityResponseDTO toResponseDTO(Amenity amenity);
    List<AmenityResponseDTO> toResponseDTOs(List<Amenity> amenities);
    AmenityCreationDTO toCreationDTO(Amenity amenity);
    AmenityUpdateDTO toUpdateDTO(Amenity amenity);

    Amenity toEntity(AmenityCreationDTO amenityCreationDTO);
    Amenity toEntity(AmenityUpdateDTO amenityUpdateDTO);
}