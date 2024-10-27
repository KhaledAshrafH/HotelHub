package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.AmenityCreationDTO;
import com.khalouda.hotelhub.model.dto.AmenityResponseDTO;
import com.khalouda.hotelhub.service.AmenityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
public class AmenityController {
    private final AmenityService amenityService;

    @PostMapping("amenities")
    public ResponseEntity<AmenityResponseDTO> createAmenity(@Valid @RequestBody AmenityCreationDTO amenityCreationDTO) {
        AmenityResponseDTO createdAmenity = amenityService.createAmenity(amenityCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAmenity);
    }

    @PostMapping("amenities/assign-to-hotel/{amenityId}/{hotelId}")
    public ResponseEntity<AmenityResponseDTO> assignAmenityToHotel(@PathVariable Long amenityId, @PathVariable Long hotelId) {
        return ResponseEntity.ok(amenityService.assignAmenityToHotel(amenityId, hotelId));
    }

    @PostMapping("amenities/assign-to-room/{amenityId}/{roomId}")
    public ResponseEntity<AmenityResponseDTO> assignAmenityToRoom(@PathVariable Long amenityId, @PathVariable Long roomId) {
        return ResponseEntity.ok(amenityService.assignAmenityToRoom(amenityId, roomId));
    }

    @PostMapping("amenities/{amenityId}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approveAmenity(@PathVariable Long amenityId) {
        amenityService.approveHotelAmenity(amenityId);
        return ResponseEntity.ok("Amenity approved");
    }

    @GetMapping("hotels/{hotelId}/amenities")
    public ResponseEntity<List<AmenityResponseDTO>> getAmenitiesForHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(amenityService.getAllAmenitiesByHotelId(hotelId));
    }

    @GetMapping("rooms/{roomId}/amenities")
    public ResponseEntity<List<AmenityResponseDTO>> getAmenitiesForRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(amenityService.getAllAmenitiesByRoomId(roomId));
    }
}
