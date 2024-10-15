package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.RoomTypeCreationDTO;
import com.khalouda.hotelhub.model.dto.RoomTypeResponseDTO;
import com.khalouda.hotelhub.model.entity.RoomType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    RoomType toEntity(RoomTypeCreationDTO roomTypeCreationDTO);

    RoomTypeResponseDTO toResponseDTO(RoomType roomType);
}


