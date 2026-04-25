package com.mibiatchi.rentals.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CarRequest(
        @NotBlank(message = "Make is required")
        String make,

        @NotBlank(message = "Model is required")
        String model,

        @NotNull(message = "Production year is required")
        @Min(value = 2000, message = "Year must be 2000 or newer")
        Integer productionYear,

        @NotNull(message = "Daily rate is required")
        @Min(value = 1, message = "Rate must be greater than 0")
        BigDecimal dailyRate,

        @NotBlank(message = "Image URL is required")
        String imageUrl
) {}