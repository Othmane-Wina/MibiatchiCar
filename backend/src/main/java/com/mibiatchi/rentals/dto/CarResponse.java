package com.mibiatchi.rentals.dto;

import com.mibiatchi.rentals.entity.CarStatus;

import java.math.BigDecimal;

public record CarResponse(
        Long id,
        String make,
        String model,
        Integer productionYear,
        BigDecimal dailyRate,
        String imageUrl,
        CarStatus status
) {}