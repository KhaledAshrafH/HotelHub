package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.BookingCreationDTO;
import com.khalouda.hotelhub.model.dto.BookingResponseDTO;
import com.khalouda.hotelhub.model.dto.BookingUpdateDTO;
import com.khalouda.hotelhub.model.entity.Booking;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponseDTO toResponseDTO(Booking booking);
    List<BookingResponseDTO> toResponseDTOs(List<Booking> bookings);
    BookingCreationDTO toCreationDTO(Booking booking);
    BookingUpdateDTO toUpdateDTO(Booking booking);

    Booking toEntity(BookingUpdateDTO bookingUpdateDTO);
    Booking toEntity(BookingCreationDTO bookingCreationDTO);

    default void updateBookingFromDTO(Booking booking, BookingUpdateDTO bookingUpdateDTO){
        if(bookingUpdateDTO.getCheckInDate()!=null)
            booking.setCheckInDate(bookingUpdateDTO.getCheckInDate());

        if(bookingUpdateDTO.getCheckOutDate()!=null)
            booking.setCheckOutDate(bookingUpdateDTO.getCheckOutDate());

        if(bookingUpdateDTO.getTotalAmount()!=null)
            booking.setTotalAmount(bookingUpdateDTO.getTotalAmount());

        if(bookingUpdateDTO.getPaymentStatus()!=null)
            booking.setPaymentStatus(bookingUpdateDTO.getPaymentStatus());
    }
}