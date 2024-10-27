package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.AmenityCreationDTO;
import com.khalouda.hotelhub.model.dto.AmenityResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomResponseDTO;

import java.util.List;

public interface AmenityService {
    AmenityResponseDTO createAmenity(AmenityCreationDTO amenityCreationDTO);
    void approveHotelAmenity(Long amenityId);
    AmenityResponseDTO assignAmenityToHotel(Long amenityId, Long hotelId);
    AmenityResponseDTO assignAmenityToRoom(Long amenityId, Long roomId);
    List<RoomResponseDTO> getAllRoomsByAmenityId(Long amenityId);
    List<AmenityResponseDTO> getAllAmenitiesByRoomId(Long roomId);
    List<AmenityResponseDTO> getAllAmenitiesByHotelId(Long hotelId);
}
