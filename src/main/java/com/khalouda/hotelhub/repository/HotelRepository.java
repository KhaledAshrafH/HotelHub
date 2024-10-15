package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository  extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByName(String name);
    List<Hotel> findAllByCity(String city);
    List<Hotel> findAllByCountry(String country);

}
