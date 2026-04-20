package com.mibiatchi.rentals.dto;

import java.math.BigDecimal;

public record CarResponse(
        Long id,
        String make,
        String model,
        Integer productionYear,
        BigDecimal dailyRate,
        String imageUrl
) {}