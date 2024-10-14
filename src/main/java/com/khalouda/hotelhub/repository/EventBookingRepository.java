package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBookingRepository  extends JpaRepository<EventBooking, Long> {
}
