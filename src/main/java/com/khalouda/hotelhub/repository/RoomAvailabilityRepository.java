package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAvailabilityRepository   extends JpaRepository<RoomAvailability, Long> {
}
