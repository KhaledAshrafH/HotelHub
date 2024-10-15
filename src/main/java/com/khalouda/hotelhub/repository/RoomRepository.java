package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.entity.Room;
import com.khalouda.hotelhub.model.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotel(Hotel hotel);
    List<Room> findAllByRoomType(RoomType roomType);
}
