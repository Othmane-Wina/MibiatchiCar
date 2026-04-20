package com.mibiatchi.rentals.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BookingRequest(
        @NotNull(message = "Car ID cannot be null")
        Long carId,

        @NotNull(message = "User ID cannot be null")
        Long userId,

        @NotNull(message = "Start date is required")
        @FutureOrPresent(message = "Start date cannot be in the past")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @FutureOrPresent(message = "End date cannot be in the past")
        LocalDate endDate
) {}