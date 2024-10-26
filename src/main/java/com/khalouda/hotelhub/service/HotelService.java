package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.*;

import java.util.List;

public interface HotelService {

    HotelResponseDTO createHotel(HotelCreationDTO hotelCreationDTO);

    HotelResponseDTO getHotelById(Long hotelId);
    List<HotelResponseDTO> getAllHotelNearMe();
    List<HotelResponseDTO> getAllHotelByCity(String city);

    List<HotelResponseDTO> getAllHotels();

    HotelResponseDTO updateHotel(Long hotelId, HotelUpdateDTO hotelUpdateDTO);

    void deleteHotel(Long hotelId);

    List<AmenityResponseDTO> getAllAmenitiesByHotelId(Long hotelId);
}
