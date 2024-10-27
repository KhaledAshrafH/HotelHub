package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.AmenityNotFoundException;
import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.exception.RoomNotFoundException;
import com.khalouda.hotelhub.model.dto.AmenityCreationDTO;
import com.khalouda.hotelhub.model.dto.AmenityResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomResponseDTO;
import com.khalouda.hotelhub.model.entity.*;
import com.khalouda.hotelhub.model.enums.AmenityType;
import com.khalouda.hotelhub.model.enums.UserRole;
import com.khalouda.hotelhub.model.mapper.RoomMapper;
import com.khalouda.hotelhub.repository.AmenityRepository;
import com.khalouda.hotelhub.repository.HotelRepository;
import com.khalouda.hotelhub.repository.RoomRepository;
import com.khalouda.hotelhub.service.AmenityService;
import com.khalouda.hotelhub.service.HotelService;
import com.khalouda.hotelhub.service.RoomService;
import com.khalouda.hotelhub.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public AmenityResponseDTO createAmenity(AmenityCreationDTO amenityCreationDTO) {
        User user = UtilityService.getCurrentUser();
        if(user.getRole()== UserRole.STAFF||user.getRole()== UserRole.ADMIN){
            Amenity amenity = new Amenity();
            AmenityResponseDTO amenityResponseDTO = new AmenityResponseDTO();
            amenity.setAmenityName(amenityCreationDTO.getAmenityName());
            amenity.setDescription(amenityCreationDTO.getDescription());
            amenity.setAmenityType(amenityCreationDTO.getAmenityType());
            if(amenity.getAmenityType()== AmenityType.HOTEL_AMENITY)
                amenity.setActive(false);

            else if(amenity.getAmenityType()== AmenityType.ROOM_AMENITY)
                amenity.setActive(true);

            amenity = amenityRepository.save(amenity);

            amenityResponseDTO.setAmenityId(amenity.getAmenityId());
            amenityResponseDTO.setName(amenity.getAmenityName());
            amenityResponseDTO.setDescription(amenity.getDescription());
            amenityResponseDTO.setAmenityType(amenity.getAmenityType());

            return amenityResponseDTO;
        }
        else
            throw new RuntimeException("You are not allowed to create an amenity");
    }

    @Override
    public void approveHotelAmenity(Long amenityId) {
        User user = UtilityService.getCurrentUser();
        if(user.getRole()== UserRole.ADMIN){
            Amenity amenity = amenityRepository.findById(amenityId).orElseThrow(() -> new AmenityNotFoundException("Amenity not found"));
            amenity.setActive(true);
            amenityRepository.save(amenity);
        }
        else
            throw new RuntimeException("You are not allowed to approve an amenity");
    }

    public AmenityResponseDTO assignAmenityToHotel(Long amenityId, Long hotelId) {
        Amenity amenity = amenityRepository.findById(amenityId).orElseThrow(() -> new AmenityNotFoundException("Amenity not found"));
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
        User user = UtilityService.getCurrentUser();
        if(!amenity.isActive())
            throw new RuntimeException("This amenity is not active");

        if(amenity.getHotel()!=null)
            throw new RuntimeException("This amenity is already assigned");

        if(user.getRole()== UserRole.STAFF||user.getRole()== UserRole.ADMIN){
            if(amenity.getAmenityType()== AmenityType.HOTEL_AMENITY){
                amenity.setHotel(hotel);
                Amenity assignedAmenity = amenityRepository.save(amenity);
                AmenityResponseDTO amenityResponseDTO = new AmenityResponseDTO();
                amenityResponseDTO.setAmenityId(assignedAmenity.getAmenityId());
                amenityResponseDTO.setName(assignedAmenity.getAmenityName());
                amenityResponseDTO.setDescription(assignedAmenity.getDescription());
                amenityResponseDTO.setAmenityType(assignedAmenity.getAmenityType());
                amenityResponseDTO.setHotelName(hotel.getName());
                return amenityResponseDTO;
            }
            else
                throw new RuntimeException("You are not allowed to assign " + amenity.getAmenityType().toString() + " to hotel");
        }
        else
            throw new RuntimeException("You are not allowed to assign an amenity");

    }

    public AmenityResponseDTO assignAmenityToRoom(Long amenityId, Long roomId) {
        Amenity amenity = amenityRepository.findById(amenityId).orElseThrow(() -> new AmenityNotFoundException("Amenity not found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        User user = UtilityService.getCurrentUser();
        if(!amenity.isActive())
            throw new RuntimeException("This amenity is not active");


        if(user.getRole()== UserRole.STAFF){
            if(room.getHotel().getHotelId().equals(((Staff) user).getHotel().getHotelId())){
                if(amenity.getAmenityType()== AmenityType.ROOM_AMENITY){

                    for(Room roomItem : amenity.getRooms())
                        if(roomItem.getRoomId().equals(room.getRoomId()))
                            throw new RuntimeException("This room is already assigned");

                    amenity.getRooms().add(room);
                    Amenity assignedAmenity = amenityRepository.save(amenity);
                    AmenityResponseDTO amenityResponseDTO = new AmenityResponseDTO();
                    amenityResponseDTO.setAmenityId(assignedAmenity.getAmenityId());
                    amenityResponseDTO.setName(assignedAmenity.getAmenityName());
                    amenityResponseDTO.setDescription(assignedAmenity.getDescription());
                    amenityResponseDTO.setAmenityType(assignedAmenity.getAmenityType());
                    amenityResponseDTO.setRoomNumber(room.getRoomNumber());
                    return amenityResponseDTO;
                }
                else
                    throw new RuntimeException("You are not allowed to assign an room to an amenity");
            }
            else
                throw new RuntimeException("You are not allowed to assign an amenity");
        }
        else
            throw new RuntimeException("You are not allowed to assign an amenity");
    }

    @Override
    public List<RoomResponseDTO> getAllRoomsByAmenityId(Long amenityId) {
        Amenity amenity = amenityRepository.findById(amenityId).orElseThrow(() -> new AmenityNotFoundException("Amenity not found"));
        List<Room> rooms = amenity.getRooms();
        return roomMapper.toResponseDTOs(rooms);
    }

    @Override
    public List<AmenityResponseDTO> getAllAmenitiesByRoomId(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        List<Amenity> amenities = amenityRepository.findAll();
        List<AmenityResponseDTO> amenityResponseDTOs = new ArrayList<>();
        for(Amenity amenity : amenities){
            if(amenity.getRooms().contains(room)){
                AmenityResponseDTO amenityResponseDTO = new AmenityResponseDTO();
                amenityResponseDTO.setAmenityId(amenity.getAmenityId());
                amenityResponseDTO.setName(amenity.getAmenityName());
                amenityResponseDTO.setDescription(amenity.getDescription());
                amenityResponseDTO.setAmenityType(amenity.getAmenityType());
                amenityResponseDTO.setRoomNumber(room.getRoomNumber());
                amenityResponseDTOs.add(amenityResponseDTO);
            }
        }
        return amenityResponseDTOs;
    }

    @Override
    public List<AmenityResponseDTO> getAllAmenitiesByHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
        List<Amenity> amenities = amenityRepository.findAllByHotel(hotel);
        List<AmenityResponseDTO> amenityResponseDTOs = new ArrayList<>();
        for(Amenity amenity : amenities){
            AmenityResponseDTO amenityResponseDTO = new AmenityResponseDTO();
            amenityResponseDTO.setAmenityId(amenity.getAmenityId());
            amenityResponseDTO.setName(amenity.getAmenityName());
            amenityResponseDTO.setDescription(amenity.getDescription());
            amenityResponseDTO.setAmenityType(amenity.getAmenityType());
            amenityResponseDTO.setHotelName(hotel.getName());
            amenityResponseDTOs.add(amenityResponseDTO);
        }
        return amenityResponseDTOs;
    }
}
