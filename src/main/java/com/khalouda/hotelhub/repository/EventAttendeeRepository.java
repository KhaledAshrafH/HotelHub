package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
}
