package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.exception.RoomNotFoundException;
import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.*;
import com.khalouda.hotelhub.model.enums.RoomStatus;
import com.khalouda.hotelhub.model.enums.UserRole;
import com.khalouda.hotelhub.model.mapper.RoomMapper;
import com.khalouda.hotelhub.model.mapper.RoomTypeMapper;
import com.khalouda.hotelhub.repository.HotelRepository;
import com.khalouda.hotelhub.repository.RoomRepository;
import com.khalouda.hotelhub.repository.RoomTypeRepository;
import com.khalouda.hotelhub.service.RoomService;
import com.khalouda.hotelhub.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;
    private final HotelRepository hotelRepository;

    @Override
    public RoomResponseDTO createRoom(RoomCreationDTO roomCreationDTO,Long hotelId) {
        Room room = roomMapper.toEntity(roomCreationDTO);
        room.setHotel(hotelRepository.findById(hotelId).get());
        room.setRoomType(roomTypeRepository.findById(roomCreationDTO.getRoomTypeId()).get());
        room.setStatus(RoomStatus.AVAILABLE);
        Room savedRoom = roomRepository.save(room);
        RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(savedRoom);
        roomResponseDTO.setHotelName(savedRoom.getHotel().getName());
        roomResponseDTO.setType(roomTypeMapper.toResponseDTO(savedRoom.getRoomType()));
        return roomResponseDTO;
    }

    @Override
    public RoomResponseDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found."));
        RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(room);
        roomResponseDTO.setHotelName(room.getHotel().getName());
        roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
        return roomResponseDTO;
    }

    @Override
    public List<RoomResponseDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
        for(Room room : rooms) {
            RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(room);
            roomResponseDTO.setHotelName(room.getHotel().getName());
            roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
            roomResponseDTOS.add(roomResponseDTO);
        }
        return roomResponseDTOS;
    }

    @Override
    public List<RoomResponseDTO> getAllRoomsOfHotel() {
        User user = UtilityService.getCurrentUser();
        if(user.getRole() == UserRole.STAFF) {
            Staff staff = (Staff) user;
            List<Room> rooms = roomRepository.findAllByHotel(staff.getHotel());
            List<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
            for(Room room : rooms) {
                RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(room);
                roomResponseDTO.setHotelName(room.getHotel().getName());
                roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
                roomResponseDTOS.add(roomResponseDTO);
            }
            return roomResponseDTOS;
        }
        else {
            // Handle unauthorized access
            throw new RuntimeException("Only staff members can get rooms.");
        }
    }

    // Staff of hotels can only edit
    @Override
    public RoomResponseDTO updateRoom(Long roomId, RoomUpdateDTO roomUpdateDTO) {
        User user = UtilityService.getCurrentUser();
        if(user.getRole() == UserRole.STAFF) {
            Staff staff = (Staff) user;
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found."));
            if (room != null && staff.getHotel().getHotelId().equals(room.getHotel().getHotelId())){
                roomMapper.updateRoomFromDTO(room,roomUpdateDTO);
                Room updatedRoom = roomRepository.save(room);
                RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(updatedRoom);
                roomResponseDTO.setHotelName(room.getHotel().getName());
                roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
                return roomResponseDTO;
            }
            else {
                // Staff doesn't belong to the hotel of the room
                throw new RuntimeException("You are not authorized to update prices for this room.");
            }
        }
        else {
            // Handle unauthorized access
            throw new RuntimeException("Only staff members can update room prices.");
        }
    }


    @Override
    public RoomResponseDTO updateRoomPrice(Long roomId, RoomUpdateDTO roomUpdateDTO) {
        if(roomUpdateDTO.getPricePerNight()==null)
            throw new RuntimeException("Price per night is required.");

        User user = UtilityService.getCurrentUser();

        if (user.getRole() == UserRole.STAFF) {
            Staff staff = (Staff) user;
            Room room = roomRepository.findById(roomId).orElse(null);

            if (room != null && staff.getHotel().getHotelId().equals(room.getHotel().getHotelId())) {
                // Staff belongs to the hotel of the room
                room.setPricePerNight(roomUpdateDTO.getPricePerNight());
                Room updatedRoom = roomRepository.save(room);
                RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(updatedRoom);
                roomResponseDTO.setHotelName(room.getHotel().getName());
                roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
                return roomResponseDTO;
            }
            else {
                // Staff doesn't belong to the hotel of the room
                throw new RuntimeException("You are not authorized to update prices for this room.");
            }
        } else {
            // Handle unauthorized access
            throw new RuntimeException("Only staff members can update room prices.");
        }
    }


    @Override
    public RoomResponseDTO updateRoomStatus(Long roomId, RoomUpdateDTO roomUpdateDTO) {
        if(roomUpdateDTO.getStatus()==null)
            throw new RuntimeException("Status is required.");

        User user = UtilityService.getCurrentUser();

        if (user.getRole() == UserRole.STAFF) {
            Staff staff = (Staff) user;
            Room room = roomRepository.findById(roomId).orElse(null);

            if (room != null && staff.getHotel().getHotelId().equals(room.getHotel().getHotelId())) {
                // Staff belongs to the hotel of the room
                room.setStatus(roomUpdateDTO.getStatus());
                Room updatedRoom = roomRepository.save(room);
                RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(updatedRoom);
                roomResponseDTO.setHotelName(room.getHotel().getName());
                roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
                return roomResponseDTO;
            }
            else {
                // Staff doesn't belong to the hotel of the room
                throw new RuntimeException("You are not authorized to update status for this room.");
            }
        } else {
            // Handle unauthorized access
            throw new RuntimeException("Only staff members can update room status.");
        }
    }

    @Override
    public void deleteRoom(Long roomId) {

        User user = UtilityService.getCurrentUser();
        if(user.getRole() == UserRole.STAFF) {
            Staff staff = (Staff) user;
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found."));
            if (room != null && staff.getHotel().getHotelId().equals(room.getHotel().getHotelId())){
                roomRepository.delete(room);
            }
            else {
                // Staff doesn't belong to the hotel of the room
                throw new RuntimeException("You are not authorized to delete this room.");
            }
        }
        else {
            // Handle unauthorized access
            throw new RuntimeException("Only staff members can update room prices.");
        }
    }

    @Override
    public RoomTypeResponseDTO addRoomType(RoomTypeCreationDTO roomTypeCreationDTO) {
        RoomType roomType = roomTypeMapper.toEntity(roomTypeCreationDTO);
        RoomType savedRoomType = roomTypeRepository.save(roomType);
        return roomTypeMapper.toResponseDTO(savedRoomType);
    }

    @Override
    public List<RoomResponseDTO> getAllRoomsByHotelId(Long hotelId) {
        List<Room> rooms = roomRepository.findAllByHotel(hotelRepository.findById(hotelId).get());
        List<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
        for(Room room : rooms) {
            RoomResponseDTO roomResponseDTO = roomMapper.toResponseDTO(room);
            roomResponseDTO.setHotelName(room.getHotel().getName());
            roomResponseDTO.setType(roomTypeMapper.toResponseDTO(room.getRoomType()));
            roomResponseDTOS.add(roomResponseDTO);
        }
        return roomResponseDTOS;
    }


}
