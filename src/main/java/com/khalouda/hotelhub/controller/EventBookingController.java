package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.service.EventBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
@RequiredArgsConstructor
public class EventBookingController {
    private final EventBookingService eventBookingService;

    @PostMapping("{hotelId}/events/event-spaces")
    public ResponseEntity<EventSpaceResponseDTO> createEventSpace(@RequestBody EventSpaceCreationDTO dto, @PathVariable Long hotelId) throws Exception {
        EventSpaceResponseDTO responseDTO = eventBookingService.createEventSpace(dto, hotelId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("{hotelId}/events/bookings")
    public ResponseEntity<EventBookingResponseDTO> bookEvent(@RequestBody EventBookingCreationDTO dto, @PathVariable Long hotelId) throws Exception {
        EventBookingResponseDTO response = eventBookingService.bookEvent(dto,hotelId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{hotelId}/events/bookings/{bookingId}/attendees")
    public ResponseEntity<EventBookingResponseDTO> addAttendeesToBooking(@PathVariable Long bookingId,@PathVariable Long hotelId, @RequestBody List<EventAttendeeDTO> attendees) {
        EventBookingResponseDTO responseDTO = eventBookingService.addAttendeesToBooking(hotelId,bookingId, attendees);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("{hotelId}/events/bookings/{bookingId}/attendees")
    public ResponseEntity<List<EventAttendeeDTO>> getAllAttendeesForBooking(@PathVariable Long bookingId,@PathVariable Long hotelId) {
        List<EventAttendeeDTO> attendees = eventBookingService.getAllAttendeesForBooking(hotelId,bookingId);
        return ResponseEntity.ok(attendees);
    }

    @DeleteMapping("{hotelId}/events/bookings/{bookingId}")
    public ResponseEntity<Void> cancelEventBooking(@PathVariable Long bookingId,@PathVariable Long hotelId) {
        eventBookingService.cancelEventBooking(bookingId,hotelId);
        return ResponseEntity.noContent().build();
    }

}
