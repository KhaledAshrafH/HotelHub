package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.model.dto.HotelCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelUpdateDTO;
import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.mapper.HotelMapper;
import com.khalouda.hotelhub.repository.HotelRepository;
import com.khalouda.hotelhub.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HotelResponseDTO createHotel(HotelCreationDTO hotelCreationDTO) {
        Hotel hotel = hotelMapper.toEntity(hotelCreationDTO);
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(savedHotel);
    }

    @Override
    public HotelResponseDTO getHotelById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() ->
                new HotelNotFoundException("Hotel with ID " + hotelId + " not found."));

        return hotelMapper.toResponseDTO(hotel);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotelMapper.toResponseDTOs(hotels);
    }

    @Override
    public HotelResponseDTO updateHotel(Long hotelId, HotelUpdateDTO hotelUpdateDTO) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() ->
                new HotelNotFoundException("Hotel with ID " + hotelId + " not found."));

        hotelMapper.updateRoomFromDTO(hotel, hotelUpdateDTO);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(updatedHotel);
    }

    @Override
    public void deleteHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() ->
                new HotelNotFoundException("Hotel with ID " + hotelId + " not found."));

        hotelRepository.delete(hotel);
    }
}
