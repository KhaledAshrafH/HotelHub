package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.*;

import java.util.List;

public interface EventBookingService {
    EventSpaceResponseDTO createEventSpace(EventSpaceCreationDTO dto,Long hotelId);
    EventBookingResponseDTO bookEvent(EventBookingCreationDTO dto,Long hotelId) throws Exception;
    EventBookingResponseDTO addAttendeesToBooking(Long hotelId,Long bookingId, List<EventAttendeeDTO> attendees);
    List<EventAttendeeDTO> getAllAttendeesForBooking(Long hotelId,Long bookingId);
    void cancelEventBooking(Long bookingId,Long hotelId);

}
