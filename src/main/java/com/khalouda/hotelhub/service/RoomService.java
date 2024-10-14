package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.*;

import java.util.List;

public interface RoomService {

    RoomResponseDTO createRoom(RoomCreationDTO roomCreationDTO);

    RoomResponseDTO getRoomById(Long room);

    List<RoomResponseDTO> getAllRooms();

    RoomResponseDTO updateRoom(Long roomId, RoomUpdateDTO roomUpdateDTO);

    void deleteRoom(Long roomId);

}
