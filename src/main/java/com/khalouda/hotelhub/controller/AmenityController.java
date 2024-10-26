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

@RequestMapping("api/v1/amenities")
@RestController
@RequiredArgsConstructor
public class AmenityController {
    private final AmenityService amenityService;

    @PostMapping("")
    public ResponseEntity<AmenityResponseDTO> createAmenity(@Valid @RequestBody AmenityCreationDTO amenityCreationDTO) {
        AmenityResponseDTO createdAmenity = amenityService.createAmenity(amenityCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAmenity);
    }

    @PostMapping("assign-to-hotel/{amenityId}/{hotelId}")
    public ResponseEntity<AmenityResponseDTO> assignAmenityToHotel(@PathVariable Long amenityId, @PathVariable Long hotelId) {
        return ResponseEntity.ok(amenityService.assignAmenityToHotel(amenityId, hotelId));
    }

    @PostMapping("assign-to-room/{amenityId}/{roomId}")
    public ResponseEntity<AmenityResponseDTO> assignAmenityToRoom(@PathVariable Long amenityId, @PathVariable Long roomId) {
        return ResponseEntity.ok(amenityService.assignAmenityToRoom(amenityId, roomId));
    }

    @PostMapping("{amenityId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approveAmenity(@PathVariable Long amenityId) {
        amenityService.approveHotelAmenity(amenityId);
        return ResponseEntity.ok("Amenity approved");
    }
}
