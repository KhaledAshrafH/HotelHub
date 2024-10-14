package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.RoomCreationDTO;
import com.khalouda.hotelhub.model.dto.RoomResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomUpdateDTO;
import com.khalouda.hotelhub.model.entity.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomResponseDTO toResponseDTO(Room Room);
    List<RoomResponseDTO> toResponseDTOs(List<Room> Rooms);
    RoomCreationDTO toCreationDTO(Room Room);
    RoomUpdateDTO toUpdateDTO(Room Room);

    Room toEntity(RoomCreationDTO RoomCreationDTO);
    Room toEntity(RoomUpdateDTO RoomUpdateDTO);

    default void updateRoomFromDTO(Room room, RoomUpdateDTO roomUpdateDTO){
        if(roomUpdateDTO.getRoomNumber()!=null)
            room.setRoomNumber(roomUpdateDTO.getRoomNumber());

        if(roomUpdateDTO.getPricePerNight()!=null)
            room.setPricePerNight(roomUpdateDTO.getPricePerNight());

        if(roomUpdateDTO.getCapacity()!=0)
            room.setCapacity(roomUpdateDTO.getCapacity());

        if(roomUpdateDTO.getStatus()!=null)
            room.setStatus(roomUpdateDTO.getStatus());
    }
}
