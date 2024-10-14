package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.HotelChainCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelChainResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelChainUpdateDTO;
import com.khalouda.hotelhub.model.entity.HotelChain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelChainMapper {
    HotelChainResponseDTO toResponseDTO(HotelChain hotelChain);
    List<HotelChainResponseDTO> toResponseDTOs(List<HotelChain> hotelChains);
    HotelChainCreationDTO toCreationDTO(HotelChain hotelChain);
    HotelChainUpdateDTO toUpdateDTO(HotelChain hotelChain);

    HotelChain toEntity(HotelChainCreationDTO hotelChainCreationDTO);
    HotelChain toEntity(HotelChainUpdateDTO hotelChainUpdateDTO);
}