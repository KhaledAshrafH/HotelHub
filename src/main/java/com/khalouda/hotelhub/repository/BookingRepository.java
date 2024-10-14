package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
