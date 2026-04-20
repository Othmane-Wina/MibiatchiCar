package com.mibiatchi.rentals.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String reviewerName,
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {}
