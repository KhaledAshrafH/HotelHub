package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Booking;
import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.entity.Room;
import com.khalouda.hotelhub.model.entity.User;
import com.khalouda.hotelhub.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUser(User user);
    List<Booking> findAllByHotel(Hotel hotel);
    List<Booking> findAllByRoomAndBookingStatus(Room room, BookingStatus status);


    Optional<Booking> findByBookingIdAndUser(Long id, User user);
}
