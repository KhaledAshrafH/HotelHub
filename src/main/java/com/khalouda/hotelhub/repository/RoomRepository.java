package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
