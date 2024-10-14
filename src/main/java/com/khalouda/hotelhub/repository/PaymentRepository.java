package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
