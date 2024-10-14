package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
