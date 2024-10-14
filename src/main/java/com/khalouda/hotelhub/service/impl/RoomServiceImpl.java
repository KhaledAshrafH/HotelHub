package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.exception.RoomNotFoundException;
import com.khalouda.hotelhub.model.dto.RoomCreationDTO;
import com.khalouda.hotelhub.model.dto.RoomResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomUpdateDTO;
import com.khalouda.hotelhub.model.entity.Room;
import com.khalouda.hotelhub.model.mapper.RoomMapper;
import com.khalouda.hotelhub.repository.RoomRepository;
import com.khalouda.hotelhub.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomResponseDTO createRoom(RoomCreationDTO roomCreationDTO) {
        Room room = roomMapper.toEntity(roomCreationDTO);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.toResponseDTO(savedRoom);
    }

    @Override
    public RoomResponseDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found."));

        return roomMapper.toResponseDTO(room);
    }

    @Override
    public List<RoomResponseDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toResponseDTOs(rooms);
    }

    @Override
    public RoomResponseDTO updateRoom(Long roomId, RoomUpdateDTO roomUpdateDTO) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found."));

        roomMapper.updateRoomFromDTO(room,roomUpdateDTO );
        Room updatedRoom = roomRepository.save(room);
        return roomMapper.toResponseDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found."));

        roomRepository.delete(room);
    }
}
