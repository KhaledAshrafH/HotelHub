package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.HotelCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelUpdateDTO;

import java.util.List;

public interface HotelService {

    HotelResponseDTO createHotel(HotelCreationDTO hotelCreationDTO);

    HotelResponseDTO getHotelById(Long hotelId);

    List<HotelResponseDTO> getAllHotels();

    HotelResponseDTO updateHotel(Long hotelId, HotelUpdateDTO hotelUpdateDTO);

    void deleteHotel(Long hotelId);

}
