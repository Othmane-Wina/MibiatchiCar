package com.mibiatchi.rentals.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long paymentId,
        Long bookingId,
        BigDecimal amountPaid,
        String status, // e.g., "SUCCESS" or "FAILED"
        String transactionId,
        LocalDateTime paymentDate
) {}