package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.BookingCreationDTO;
import com.khalouda.hotelhub.model.dto.BookingResponseDTO;
import com.khalouda.hotelhub.model.dto.BookingUpdateDTO;

import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(BookingCreationDTO bookingCreationDTO,Long roomId);

    BookingResponseDTO getUserBookingById(Long bookingId);
    List<BookingResponseDTO> getAllUserBookings();
    List<BookingResponseDTO> getAllHotelBookings(Long hotelId);
    List<BookingResponseDTO> getAllBookings();
    BookingResponseDTO updateBooking(Long bookingId, BookingUpdateDTO bookingUpdateDTO);
    void cancelBooking(Long bookingId);

    List<BookingResponseDTO> getAllGuestBookings(Long guestId);
}
