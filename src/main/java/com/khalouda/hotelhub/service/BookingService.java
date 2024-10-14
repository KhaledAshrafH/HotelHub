package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.BookingCreationDTO;
import com.khalouda.hotelhub.model.dto.BookingResponseDTO;
import com.khalouda.hotelhub.model.dto.BookingUpdateDTO;

import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(BookingCreationDTO bookingCreationDTO);

    BookingResponseDTO getBookingById(Long bookingId);

    List<BookingResponseDTO> getAllBookings();

    BookingResponseDTO updateBooking(Long bookingId, BookingUpdateDTO bookingUpdateDTO);

    void cancelBooking(Long bookingId);
}
