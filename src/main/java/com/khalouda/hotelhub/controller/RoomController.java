package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.RoomType;
import com.khalouda.hotelhub.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("rooms")
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<RoomResponseDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("hotels/{hotelId}/rooms")
    public ResponseEntity<List<RoomResponseDTO>> getAllRoomsByHotelId(@PathVariable(name = "hotelId") Long hotelId) {
        List<RoomResponseDTO> rooms = roomService.getAllRoomsByHotelId(hotelId);
        return ResponseEntity.ok(rooms);
    }


    // For staffs of hotel only
    @GetMapping("rooms/me")
    public ResponseEntity<List<RoomResponseDTO>> getAllRoomsByStaffHotel() {
        List<RoomResponseDTO> rooms = roomService.getAllRoomsOfHotel();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("rooms/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable(name = "id") Long roomId) {
        RoomResponseDTO room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    @PostMapping("hotels/{hotelId}/rooms")
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody RoomCreationDTO roomCreationDTO,@PathVariable(name = "hotelId") Long hotelId) {
        RoomResponseDTO createdRoom = roomService.createRoom(roomCreationDTO,hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @PutMapping("rooms/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable("id") Long roomId,
            @Valid @RequestBody RoomUpdateDTO roomUpdateDTO) {

        RoomResponseDTO updatedRoom = roomService.updateRoom(roomId, roomUpdateDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @PutMapping("rooms/{id}/price")
    public ResponseEntity<RoomResponseDTO> updateRoomPrice(
            @PathVariable("id") Long roomId,
            @Valid @RequestBody RoomUpdateDTO roomUpdateDTO) {

        RoomResponseDTO updatedRoom = roomService.updateRoomPrice(roomId, roomUpdateDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @PutMapping("rooms/{id}/status")
    public ResponseEntity<RoomResponseDTO> updateRoomStatus(
            @PathVariable("id") Long roomId,
            @Valid @RequestBody RoomUpdateDTO roomUpdateDTO) {

        RoomResponseDTO updatedRoom = roomService.updateRoomStatus(roomId, roomUpdateDTO);
        return ResponseEntity.ok(updatedRoom);
    }


    @DeleteMapping("rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("rooms/types")
    public ResponseEntity<RoomTypeResponseDTO> createRoomType(@Valid @RequestBody RoomTypeCreationDTO roomTypeCreationDTO) {
        RoomTypeResponseDTO createdRoomType = roomService.addRoomType(roomTypeCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomType);
    }
}
