package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.exception.BookingException;
import com.khalouda.hotelhub.model.dto.BookingCreationDTO;
import com.khalouda.hotelhub.model.dto.BookingResponseDTO;
import com.khalouda.hotelhub.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestParam("roomId") Long roomId,
                                                                @RequestBody BookingCreationDTO bookingCreationDTO) {
        return new ResponseEntity<>(bookingService.createBooking(bookingCreationDTO, roomId),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") Long bookingId)
            throws BookingException {
        bookingService.cancelBooking(bookingId);
        return new ResponseEntity<>("Booking Canceled Successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("hotelId")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingsByHotel(@RequestParam(name = "hotelId") Long hotelId) {
        return new ResponseEntity<>(bookingService.getAllHotelBookings(hotelId), HttpStatus.OK);
    }

    @GetMapping("guestId")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingsByGuest(@RequestParam(name = "guestId") Long guestId) {
        return new ResponseEntity<>(bookingService.getAllGuestBookings(guestId), HttpStatus.OK);
    }


    @GetMapping("me")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingsByUser() {
        return new ResponseEntity<>(bookingService.getAllUserBookings(), HttpStatus.OK);
    }

    @GetMapping("{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable("bookingId") Long bookingId) {
        return new ResponseEntity<>(bookingService.getUserBookingById(bookingId), HttpStatus.OK);
    }
}
