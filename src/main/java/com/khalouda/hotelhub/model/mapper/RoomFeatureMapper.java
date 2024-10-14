package com.khalouda.hotelhub.model.mapper;


import com.khalouda.hotelhub.model.dto.RoomFeatureCreationDTO;
import com.khalouda.hotelhub.model.dto.RoomFeatureResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomFeatureUpdateDTO;
import com.khalouda.hotelhub.model.entity.RoomFeature;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomFeatureMapper {
    RoomFeatureResponseDTO toResponseDTO(RoomFeature roomFeature);
    List<RoomFeatureResponseDTO> toResponseDTOs(List<RoomFeature> roomFeatures);
    RoomFeatureCreationDTO toCreationDTO(RoomFeature roomFeature);
    RoomFeatureUpdateDTO toUpdateDTO(RoomFeature roomFeature);

    RoomFeature toEntity(RoomFeatureCreationDTO roomFeatureCreationDTO);
    RoomFeature toEntity(RoomFeatureUpdateDTO roomFeatureUpdateDTO);
}