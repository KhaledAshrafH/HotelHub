package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
