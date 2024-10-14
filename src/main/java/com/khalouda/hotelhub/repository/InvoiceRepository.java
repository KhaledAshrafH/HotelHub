package com.khalouda.hotelhub.repository;

import com.khalouda.hotelhub.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
}
