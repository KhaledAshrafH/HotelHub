package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.BookingException;
import com.khalouda.hotelhub.exception.BookingNotFoundException;
import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.*;
import com.khalouda.hotelhub.model.enums.BookingStatus;
import com.khalouda.hotelhub.repository.EventAttendeeRepository;
import com.khalouda.hotelhub.repository.EventBookingRepository;
import com.khalouda.hotelhub.repository.EventSpaceRepository;
import com.khalouda.hotelhub.repository.HotelRepository;
import com.khalouda.hotelhub.service.EventBookingService;
import com.khalouda.hotelhub.service.UtilityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;
    private final EventSpaceRepository eventSpaceRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final HotelRepository hotelRepository;

    @Override
    public EventSpaceResponseDTO createEventSpace(EventSpaceCreationDTO dto,Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
        EventSpace eventSpace = EventSpace.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .hotel(hotel)
                .capacity(dto.getCapacity())
                .build();
        EventSpace savedEventSpace = eventSpaceRepository.save(eventSpace);
        return EventSpaceResponseDTO.builder()
                .eventSpaceId(savedEventSpace.getEventSpaceId())
                .name(savedEventSpace.getName())
                .description(savedEventSpace.getDescription())
                .price(savedEventSpace.getPrice())
                .capacity(savedEventSpace.getCapacity())
                .createdAt(savedEventSpace.getCreatedAt())
                .updatedAt(savedEventSpace.getUpdatedAt())
                .build();
    }

    @Override
    public EventBookingResponseDTO bookEvent(EventBookingCreationDTO dto,Long hotelId) throws Exception {
        User user = UtilityService.getCurrentUser();
        EventSpace eventSpace = eventSpaceRepository.findById(dto.getEventSpaceId())
                .orElseThrow(() -> new Exception("Event space not found"));


        EventBooking eventBooking = EventBooking.builder()
                .eventDateTime(dto.getEventDateTime())
                .duration(Duration.ofMinutes(dto.getDurationInMinutes()))
                .eventSpace(eventSpace)
                .user(user)
                .eventDateTime(dto.getEventDateTime())
                .build();

        LocalDateTime checkIn = dto.getEventDateTime();
        LocalDateTime checkOut = dto.getEventDateTime().plusMinutes(dto.getDurationInMinutes());
        log.info("Checking Room availability for dates : {} -> {}", checkIn, checkOut);
        List<EventBooking> existingEventBookings = eventBookingRepository.findAllByEventSpaceAndBookingStatus(eventSpace,BookingStatus.BOOKED);

        // Check for overlapping bookings
        for (EventBooking existingEventBooking : existingEventBookings) {
            // Overlap occurs if:
            // - New check-in falls within existing booking period
            // - New check-out falls within existing booking period
            // - New booking period completely encompasses the existing booking
            if (isOverlapping(existingEventBooking.getEventDateTime(), existingEventBooking.getEventDateTime().plusMinutes(existingEventBooking.getDuration().toMinutes()), checkIn, checkOut)){
                eventBooking.setBookingStatus(BookingStatus.CLOSED);
                eventBookingRepository.save(eventBooking);
                throw new BookingException("Event not available for this date! Your payment will be transferred back to your account within 15 minutes");
            }


        }
        eventBooking.setBookingStatus(BookingStatus.BOOKED);
        eventBookingRepository.save(eventBooking);
        return EventBookingResponseDTO.builder()
                .eventBookingId(eventBooking.getEventBookingId())
                .eventDateTime(eventBooking.getEventDateTime())
                .duration(eventBooking.getDuration())
                .createdAt(eventBooking.getCreatedAt())
                .updatedAt(eventBooking.getUpdatedAt())
                .build();
    }

    public EventBookingResponseDTO addAttendeesToBooking(Long hotelId,Long bookingId, List<EventAttendeeDTO> attendees) {
        EventBooking booking = eventBookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Event booking not found"));

        // Create EventAttendee entities with a single operation
        List<EventAttendee> eventAttendees = attendees.stream()
                .map(dto -> EventAttendee.builder()
                        .name(dto.getName())
                        .email(dto.getEmail())
                        .eventBooking(booking)
                        .build())
                .toList();

        System.out.println(eventAttendees);
        eventAttendeeRepository.saveAll(eventAttendees.stream().toList());


        List<EventAttendeeDTO> eventAttendeeDTOS = new ArrayList<>();
        for(EventAttendee eventAttendee : eventAttendees) {
            EventAttendeeDTO eventAttendeeDTO = new EventAttendeeDTO();
            eventAttendeeDTO.setName(eventAttendee.getName());
            eventAttendeeDTO.setEmail(eventAttendee.getEmail());
            eventAttendeeDTOS.add(eventAttendeeDTO);
        }
        // Build the response DTO with a single stream operation
        return EventBookingResponseDTO.builder()
                .attendees(eventAttendeeDTOS) // Directly use the attendees list
                .eventBookingId(booking.getEventBookingId())
                .eventDateTime(booking.getEventDateTime())
                .duration(booking.getDuration())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .build();
    }

    public List<EventAttendeeDTO> getAllAttendeesForBooking(Long hotelId,Long bookingId) {
        EventBooking booking = eventBookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Event booking not found"));

        return booking.getAttendees().stream()
                .map(attendee -> EventAttendeeDTO.builder()
                        .attendeeId(attendee.getAttendeeId())
                        .name(attendee.getName())
                        .email(attendee.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public void cancelEventBooking(Long bookingId,Long hotelId) {
        User user = UtilityService.getCurrentUser();
        EventBooking booking = eventBookingRepository.findById(bookingId).orElseThrow(() ->
                new BookingNotFoundException("Event Booking with ID " + bookingId + " not found."));

        if(booking.getEventDateTime().equals(LocalDateTime.now())||booking.getEventDateTime().isBefore(LocalDateTime.now()))
            throw new BookingException("You are not allowed to cancel this booking now!");

        if(!booking.getUser().getId().equals(user.getId()))
            throw new BookingException("You are not allowed to cancel this booking");

        booking.setBookingStatus(BookingStatus.CLOSED);
        eventBookingRepository.save(booking);
        log.info("Booking cancelled successfully");
    }

    // Helper method to check for overlapping date ranges
    private boolean isOverlapping(LocalDateTime existingCheckIn, LocalDateTime existingCheckOut, LocalDateTime newCheckIn, LocalDateTime newCheckOut) {
        return (newCheckIn.isBefore(existingCheckOut.plusDays(1)) && newCheckIn.isAfter(existingCheckIn.minusDays(1)))
                || (newCheckOut.isBefore(existingCheckOut.plusDays(1)) && newCheckOut.isAfter(existingCheckIn.minusDays(1)))
                || (existingCheckIn.isBefore(newCheckOut.plusDays(1)) && existingCheckIn.isAfter(newCheckIn.minusDays(1)))
                || (existingCheckOut.isBefore(newCheckOut.plusDays(1)) && existingCheckOut.isAfter(newCheckIn.minusDays(1)));
    }

}
