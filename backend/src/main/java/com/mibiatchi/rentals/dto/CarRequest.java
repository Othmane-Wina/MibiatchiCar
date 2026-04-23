package com.mibiatchi.rentals.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CarRequest(
        @NotBlank(message = "Make is required") String make,
        @NotBlank(message = "Model is required") String model,
        @NotNull(message = "Production year is required") Integer productionYear,
        @NotNull(message = "Daily rate is required") @Min(0) BigDecimal dailyRate,
        String imageUrl,
        boolean isAvailable
) {}