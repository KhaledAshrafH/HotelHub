package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.HotelChainCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelChainResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelResponseDTO;

import java.util.List;

public interface HotelChainService {
    HotelChainResponseDTO createHotelChain(HotelChainCreationDTO creationDTO);

    HotelChainResponseDTO addHotelToChain(Long hotelId, Long chainId);

    HotelChainResponseDTO getHotelChainById(Long chainId);

    List<HotelResponseDTO> getHotelsOfChain(Long chainId);
}
