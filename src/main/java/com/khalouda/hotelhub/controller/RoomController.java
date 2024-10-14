package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.RoomCreationDTO;
import com.khalouda.hotelhub.model.dto.RoomResponseDTO;
import com.khalouda.hotelhub.model.dto.RoomUpdateDTO;
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
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody RoomCreationDTO roomCreationDTO) {
        RoomResponseDTO createdRoom = roomService.createRoom(roomCreationDTO);
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
}
