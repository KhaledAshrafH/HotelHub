package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.BookingException;
import com.khalouda.hotelhub.exception.BookingNotFoundException;
import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.exception.RoomNotFoundException;
import com.khalouda.hotelhub.model.dto.BookingCreationDTO;
import com.khalouda.hotelhub.model.dto.BookingResponseDTO;
import com.khalouda.hotelhub.model.dto.BookingUpdateDTO;
import com.khalouda.hotelhub.model.dto.NotificationCreationDTO;
import com.khalouda.hotelhub.model.entity.*;
import com.khalouda.hotelhub.model.enums.BookingStatus;
import com.khalouda.hotelhub.model.enums.NotificationType;
import com.khalouda.hotelhub.model.enums.UserRole;
import com.khalouda.hotelhub.model.mapper.BookingMapper;
import com.khalouda.hotelhub.repository.*;
import com.khalouda.hotelhub.service.BookingService;
import com.khalouda.hotelhub.service.NotificationService;
import com.khalouda.hotelhub.service.UtilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;
    private final NotificationService notificationService;


    @Override
    public BookingResponseDTO createBooking(BookingCreationDTO bookingCreationDTO,Long roomId) {
        User user = UtilityService.getCurrentUser();
        Room room =roomRepository.findById(roomId).orElseThrow(() -> new RoomNotFoundException("room with id "+roomId+" not found"));
        Hotel hotel = room.getHotel();
        Booking booking = bookingMapper.toEntity(bookingCreationDTO);

        List<Booking> existingBookings = bookingRepository.findAllByRoomAndBookingStatus(room, BookingStatus.BOOKED);

        LocalDateTime checkIn = bookingCreationDTO.getCheckInDate();
        LocalDateTime checkOut = bookingCreationDTO.getCheckOutDate();
        log.info("Checking Room availability for dates : {} -> {}", checkIn, checkOut);

        booking.setUser(user);
        booking.setRoom(room);
        booking.setHotel(hotel);

        // Check for overlapping bookings
        for (Booking existingBooking : existingBookings) {
            // Overlap occurs if:
            // - New check-in falls within existing booking period
            // - New check-out falls within existing booking period
            // - New booking period completely encompasses the existing booking
            if (isOverlapping(existingBooking.getCheckInDate(), existingBooking.getCheckOutDate(), checkIn, checkOut)){
                booking.setBookingStatus(BookingStatus.CLOSED);
                bookingRepository.save(booking);
                throw new BookingException("Room not available for this date! Your payment will be transferred back to your account within 15 minutes");
            }


        }


        booking.setBookingStatus(BookingStatus.BOOKED);
        Booking savedBooking = bookingRepository.save(booking);
        log.info("Booking successfully");
        NotificationCreationDTO notification = NotificationCreationDTO.builder()
                .userId(user.getId())
                .message("Your booking has been confirmed.")
                .type(NotificationType.BOOKING_CONFIRMED)
                .build();
        notificationService.sendNotification(notification);
        BookingResponseDTO bookingResponseDTO = bookingMapper.toResponseDTO(savedBooking);
        bookingResponseDTO.setUserId(user.getId());
        bookingResponseDTO.setRoomId(room.getRoomId());
        return bookingResponseDTO;
    }


    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new BookingNotFoundException("Booking with ID " + bookingId + " not found."));
        User user = UtilityService.getCurrentUser();
        if(!booking.getUser().getId().equals(user.getId()))
            throw new BookingException("You are not allowed to cancel this booking");

        if(booking.getCheckInDate().equals(LocalDateTime.now())||booking.getCheckInDate().isBefore(LocalDateTime.now()))
            throw new BookingException("You are not allowed to cancel this booking now!");

        booking.setBookingStatus(BookingStatus.CLOSED);
        bookingRepository.save(booking);
        log.info("Booking cancelled successfully");
    }



    @Override
    public BookingResponseDTO getUserBookingById(Long bookingId) {
        User user = UtilityService.getCurrentUser();
        Booking booking = bookingRepository.findByBookingIdAndUser(bookingId,user)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found."));
        return bookingMapper.toResponseDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getAllUserBookings() {
        User user = UtilityService.getCurrentUser();
        List<Booking> bookings;
        if(user.getRole()==UserRole.GUEST)
            bookings = bookingRepository.findAllByUser(user);

        else if(user.getRole()==UserRole.STAFF){
            Hotel hotel = hotelRepository.findById(((Staff) user).getHotel().getHotelId()).orElseThrow(() ->
                    new HotelNotFoundException("Hotel with ID " + ((Staff) user).getHotel().getHotelId() + " not found."));
            bookings = bookingRepository.findAllByHotel(hotel);
        }
        else
            bookings = bookingRepository.findAll();
        List<BookingResponseDTO> bookingResponseDTOS = bookingMapper.toResponseDTOs(bookings);
        for(BookingResponseDTO bookingResponseDTO : bookingResponseDTOS)
            bookingResponseDTO.setUserId(user.getId());
        return bookingResponseDTOS;
    }

    @Override
    public List<BookingResponseDTO> getAllHotelBookings(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new HotelNotFoundException("Hotel with id " + hotelId + " not found."));
        List<Booking> bookings = bookingRepository.findAllByHotel(hotel);
        return bookingMapper.toResponseDTOs(bookings);
    }

    @Override
    public List<BookingResponseDTO> getAllGuestBookings(Long guestId) {
        User currentUser = UtilityService.getCurrentUser();
        User user = userRepository.findById(guestId).orElseThrow(()-> new RuntimeException("User not found"));
        if(currentUser.getRole()==UserRole.GUEST&&user.getId().equals(currentUser.getId())){
            List<Booking> bookings = bookingRepository.findAllByUser(user);
            return bookingMapper.toResponseDTOs(bookings);
        }
        else
            throw new RuntimeException("You are not allowed to get guest bookings");
    }


    @Override
    public List<BookingResponseDTO> getAllBookings() {
        User user = UtilityService.getCurrentUser();
        if(user.getRole()== UserRole.ADMIN){
            List<Booking> bookings = bookingRepository.findAll();
            return bookingMapper.toResponseDTOs(bookings);
        }
        else
            throw new RuntimeException("You are not allowed to view this booking");
    }

    @Override
    public BookingResponseDTO updateBooking(Long bookingId, BookingUpdateDTO bookingUpdateDTO) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new BookingNotFoundException("Booking with ID " + bookingId + " not found."));

        bookingMapper.updateBookingFromDTO(booking, bookingUpdateDTO);
        Booking updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toResponseDTO(updatedBooking);
    }

    // Helper method to check for overlapping date ranges
    private boolean isOverlapping(LocalDateTime existingCheckIn, LocalDateTime existingCheckOut, LocalDateTime newCheckIn, LocalDateTime newCheckOut) {
        return (newCheckIn.isBefore(existingCheckOut.plusDays(1)) && newCheckIn.isAfter(existingCheckIn.minusDays(1)))
                || (newCheckOut.isBefore(existingCheckOut.plusDays(1)) && newCheckOut.isAfter(existingCheckIn.minusDays(1)))
                || (existingCheckIn.isBefore(newCheckOut.plusDays(1)) && existingCheckIn.isAfter(newCheckIn.minusDays(1)))
                || (existingCheckOut.isBefore(newCheckOut.plusDays(1)) && existingCheckOut.isAfter(newCheckIn.minusDays(1)));
    }

}
