package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.EventBooking;
import com.khalouda.hotelhub.model.entity.EventSpace;
import com.khalouda.hotelhub.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventBookingRepository  extends JpaRepository<EventBooking, Long> {
    List<EventBooking> findAllByEventSpaceAndBookingStatus(EventSpace eventSpace, BookingStatus bookingStatus);
}
