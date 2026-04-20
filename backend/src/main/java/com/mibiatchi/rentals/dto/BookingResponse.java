package com.mibiatchi.rentals.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingResponse(
        Long bookingId,
        Long carId,
        String carMakeModel, // e.g., "Dacia Logan" - combined for easy frontend display
        LocalDate startDate,
        LocalDate endDate,
        String status,
        BigDecimal totalPrice
) {}