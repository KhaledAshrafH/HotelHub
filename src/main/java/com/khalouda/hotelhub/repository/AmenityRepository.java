package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
