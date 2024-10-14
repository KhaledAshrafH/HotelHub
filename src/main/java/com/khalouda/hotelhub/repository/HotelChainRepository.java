package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.HotelChain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelChainRepository  extends JpaRepository<HotelChain, Long> {
}
