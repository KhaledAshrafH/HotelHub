package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
