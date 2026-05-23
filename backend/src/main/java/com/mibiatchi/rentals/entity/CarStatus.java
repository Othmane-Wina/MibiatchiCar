package com.mibiatchi.rentals.entity;

public enum CarStatus {
    AVAILABLE,         // Sitting in the lot, ready to go
    RENTED,         // Currently driven by a customer
    MAINTENANCE,    // In the garage for repairs
    RETIRED_SOLD    // Hit the 5-year limit and was sold
}