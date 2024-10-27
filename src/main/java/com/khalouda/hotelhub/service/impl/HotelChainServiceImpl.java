package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.model.dto.HotelChainCreationDTO;
import com.khalouda.hotelhub.model.dto.HotelChainResponseDTO;
import com.khalouda.hotelhub.model.dto.HotelResponseDTO;
import com.khalouda.hotelhub.model.entity.Hotel;
import com.khalouda.hotelhub.model.entity.HotelChain;
import com.khalouda.hotelhub.model.mapper.HotelChainMapper;
import com.khalouda.hotelhub.model.mapper.HotelMapper;
import com.khalouda.hotelhub.repository.HotelChainRepository;
import com.khalouda.hotelhub.repository.HotelRepository;
import com.khalouda.hotelhub.service.HotelChainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelChainServiceImpl implements HotelChainService {
    private final HotelChainRepository hotelChainRepository;
    private final HotelChainMapper hotelChainMapper;
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HotelChainResponseDTO createHotelChain(HotelChainCreationDTO creationDTO) {
        HotelChain chain = hotelChainMapper.toEntity(creationDTO);
        HotelChain savedChain = hotelChainRepository.save(chain);
        return hotelChainMapper.toResponseDTO(savedChain);
    }

    @Override
    public HotelChainResponseDTO addHotelToChain(Long hotelId, Long chainId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
        HotelChain hotelChain = hotelChainRepository.findById(chainId).orElseThrow(() -> new HotelNotFoundException("HotelChain not found"));
        hotel.setHotelChain(hotelChain);
        hotelRepository.save(hotel);
        HotelChain savedChain = hotelChainRepository.findById(chainId).get();
        if(savedChain.getHotels()==null)
            savedChain.setHotels(new ArrayList<>());
        savedChain.getHotels().add(hotel);
        return hotelChainMapper.toResponseDTO(savedChain);
    }

    @Override
    public HotelChainResponseDTO getHotelChainById(Long chainId) {
        HotelChain hotelChain = hotelChainRepository.findById(chainId).orElseThrow(() ->
                new RuntimeException("Hotel chain with ID " + chainId + " not found."));
        return hotelChainMapper.toResponseDTO(hotelChain);
    }

    @Override
    public List<HotelResponseDTO> getHotelsOfChain(Long chainId) {
        HotelChain hotelChain = hotelChainRepository.findById(chainId).orElseThrow(() ->
                new RuntimeException("Hotel chain with ID " + chainId + " not found."));
        List<Hotel> hotels = new ArrayList<>(hotelChain.getHotels());
        return hotelMapper.toResponseDTOs(hotels);
    }
}
