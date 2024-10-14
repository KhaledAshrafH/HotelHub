package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.LoyaltyPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long> {
}
