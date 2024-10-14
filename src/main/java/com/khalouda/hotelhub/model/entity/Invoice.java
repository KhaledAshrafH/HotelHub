package com.khalouda.hotelhub.model.entity;

import com.khalouda.hotelhub.model.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;
    @Column(name = "billing_address")
    private String billingAddress;
    @Column(name = "billing_email")
    private String billingEmail;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "tax_amount")
    private BigDecimal taxAmount;
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;
    @Column(name = "final_amount")
    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    private java.sql.Date issuedAt;
    private java.sql.Date dueDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;
}
