package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository  extends JpaRepository<Staff, Long> {
}
