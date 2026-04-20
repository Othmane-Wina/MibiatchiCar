package com.mibiatchi.rentals.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReviewRequest(
        @NotNull Long carId,
        @NotNull Long userId,
        @Min(1) @Max(5) Integer rating,
        @NotBlank String comment
) {}