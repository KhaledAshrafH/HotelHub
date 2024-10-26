package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.HotelCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelUpdateDTO;
import com.khalouda.hotelhub.model.dto.RoomResponseDTO;
import com.khalouda.hotelhub.service.HotelService;
import com.khalouda.hotelhub.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;


    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody HotelCreationDTO hotelCreationDTO) {
        HotelResponseDTO createdHotel = hotelService.createHotel(hotelCreationDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable(name = "id") Long id) {
        HotelResponseDTO hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("")
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        List<HotelResponseDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("search")
    public ResponseEntity<List<HotelResponseDTO>> getAllHotelsByCity(@RequestParam(name = "city") String city) {
        List<HotelResponseDTO> hotels = hotelService.getAllHotelByCity(city);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("search/near-me")
    public ResponseEntity<List<HotelResponseDTO>> getAllHotelsNearMe() {
        List<HotelResponseDTO> hotels = hotelService.getAllHotelNearMe();
        return ResponseEntity.ok(hotels);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable(name = "id") Long id,
                                                        @RequestBody HotelUpdateDTO hotelUpdateDTO) {
        HotelResponseDTO updatedHotel = hotelService.updateHotel(id, hotelUpdateDTO);
        return ResponseEntity.ok(updatedHotel);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHotel(@PathVariable(name = "id") Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
