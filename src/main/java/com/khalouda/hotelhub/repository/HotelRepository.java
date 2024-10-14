package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository  extends JpaRepository<Hotel, Long> {
}
