package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreationDTO {
    private Long bookingId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
}
