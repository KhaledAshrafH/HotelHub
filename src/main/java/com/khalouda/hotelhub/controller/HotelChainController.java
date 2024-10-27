package com.khalouda.hotelhub.controller;


import com.khalouda.hotelhub.model.dto.HotelChainCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelChainResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelResponseDTO;
import com.khalouda.hotelhub.service.HotelChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel-chains")
@RequiredArgsConstructor
public class HotelChainController {
    private final HotelChainService hotelChainService;

    @PostMapping("")
    public ResponseEntity<HotelChainResponseDTO> createHotelChain(@RequestBody HotelChainCreationDTO creationDTO) {
        HotelChainResponseDTO responseDTO = hotelChainService.createHotelChain(creationDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{chainId}/hotels/{hotelId}")
    public ResponseEntity<HotelChainResponseDTO> addHotelToChain(@PathVariable Long chainId, @PathVariable Long hotelId) {
        HotelChainResponseDTO responseDTO = hotelChainService.addHotelToChain(hotelId, chainId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{chainId}")
    public ResponseEntity<HotelChainResponseDTO> getHotelChainById(@PathVariable Long chainId) {
        HotelChainResponseDTO responseDTO = hotelChainService.getHotelChainById(chainId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{chainId}/hotels")
    public ResponseEntity<List<HotelResponseDTO>> getHotelsOfChain(@PathVariable Long chainId) {
        List<HotelResponseDTO> hotelsOfChain = hotelChainService.getHotelsOfChain(chainId);
        return ResponseEntity.ok(hotelsOfChain);
    }
}
