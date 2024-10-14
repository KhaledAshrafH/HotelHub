package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.PaymentMethod;
import com.khalouda.hotelhub.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentUpdateDTO {
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private PaymentStatus status;
}
