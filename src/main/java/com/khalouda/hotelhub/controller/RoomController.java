package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.RoomType;
import com.khalouda.hotelhub.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("")
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<RoomResponseDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable(name = "id") Long roomId) {
        RoomResponseDTO room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    @PostMapping("")
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody RoomCreationDTO roomCreationDTO,@RequestParam(name = "hotelId") Long hotelId) {
        RoomResponseDTO createdRoom = roomService.createRoom(roomCreationDTO,hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable("id") Long roomId,
            @Valid @RequestBody RoomUpdateDTO roomUpdateDTO) {

        RoomResponseDTO updatedRoom = roomService.updateRoom(roomId, roomUpdateDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("room-type")
    public ResponseEntity<RoomTypeResponseDTO> createRoomType(@Valid @RequestBody RoomTypeCreationDTO roomTypeCreationDTO) {
        RoomTypeResponseDTO createdRoomType = roomService.addRoomType(roomTypeCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomType);
    }

}
