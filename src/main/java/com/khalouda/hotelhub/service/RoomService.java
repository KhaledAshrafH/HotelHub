package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.RoomType;

import java.util.List;

public interface RoomService {

    RoomResponseDTO createRoom(RoomCreationDTO roomCreationDTO,Long hotelId);

    RoomResponseDTO getRoomById(Long room);

    List<RoomResponseDTO> getAllRooms();

    RoomResponseDTO updateRoom(Long roomId, RoomUpdateDTO roomUpdateDTO);
    RoomResponseDTO updateRoomPrice(Long roomId, RoomUpdateDTO roomUpdateDTO);
    RoomResponseDTO updateRoomStatus(Long roomId, RoomUpdateDTO roomUpdateDTO);

    void deleteRoom(Long roomId);

    RoomTypeResponseDTO addRoomType(RoomTypeCreationDTO roomTypeCreationDTO);

    List<RoomResponseDTO> getAllRoomsByHotelId(Long hotelId);
    List<RoomResponseDTO> getAllRoomsOfHotel();
}
