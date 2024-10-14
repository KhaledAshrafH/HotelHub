package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.BookingNotFoundException;
import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.model.dto.BookingCreationDTO;
import com.khalouda.hotelhub.model.dto.BookingResponseDTO;
import com.khalouda.hotelhub.model.dto.BookingUpdateDTO;
import com.khalouda.hotelhub.model.entity.Booking;
import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.mapper.BookingMapper;
import com.khalouda.hotelhub.repository.BookingRepository;
import com.khalouda.hotelhub.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
//    private final RoomService roomService;

    @Override
    public BookingResponseDTO createBooking(BookingCreationDTO bookingCreationDTO) {
        Booking booking = bookingMapper.toEntity(bookingCreationDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(savedBooking);
    }

    @Override
    public BookingResponseDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found."));
        return bookingMapper.toResponseDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toResponseDTOs(bookings);
    }

    @Override
    public BookingResponseDTO updateBooking(Long bookingId, BookingUpdateDTO bookingUpdateDTO) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new BookingNotFoundException("Booking with ID " + bookingId + " not found."));

        bookingMapper.updateBookingFromDTO(booking, bookingUpdateDTO);
        Booking updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(updatedBooking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new BookingNotFoundException("Booking with ID " + bookingId + " not found."));

        bookingRepository.delete(booking);
    }

}
