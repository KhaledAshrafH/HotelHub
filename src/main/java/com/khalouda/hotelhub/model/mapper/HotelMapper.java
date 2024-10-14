package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.entity.HotelChain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelResponseDTO toResponseDTO(Hotel hotel);
    List<HotelResponseDTO> toResponseDTOs(List<Hotel> hotels);
    HotelCreationDTO toCreationDTO(Hotel hotel);
    HotelUpdateDTO toUpdateDTO(Hotel hotel);

    Hotel toEntity(HotelCreationDTO hotelCreationDTO);
    Hotel toEntity(HotelUpdateDTO hotelUpdateDTO);

    default void updateRoomFromDTO(Hotel hotel, HotelUpdateDTO hotelUpdateDTO){

        if (hotelUpdateDTO.getName() != null)
            hotel.setName(hotelUpdateDTO.getName());

        if(hotelUpdateDTO.getAddress() != null)
            hotel.setAddress(hotelUpdateDTO.getAddress());

        if(hotelUpdateDTO.getPhone() != null)
            hotel.setPhone(hotelUpdateDTO.getPhone());

        if(hotelUpdateDTO.getEmail() != null)
            hotel.setEmail(hotelUpdateDTO.getEmail());

        if(hotelUpdateDTO.getCity() != null)
            hotel.setCity(hotelUpdateDTO.getCity());

        if(hotelUpdateDTO.getState() != null)
            hotel.setState(hotelUpdateDTO.getState());

        if(hotelUpdateDTO.getCountry() != null)
            hotel.setCountry(hotelUpdateDTO.getCountry());
    }
}
