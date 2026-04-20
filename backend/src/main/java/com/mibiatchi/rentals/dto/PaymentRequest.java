package com.mibiatchi.rentals.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotNull(message = "Booking ID is required")
        Long bookingId,

        @NotBlank(message = "Payment token is required")
        String paymentToken // This would be the Stripe/PayPal token
) {}