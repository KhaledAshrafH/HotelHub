package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Amenity;
import com.khalouda.hotelhub.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    List<Amenity> findAllByHotel(Hotel hotel);
}
