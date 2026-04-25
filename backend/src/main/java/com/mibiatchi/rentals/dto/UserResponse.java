package com.mibiatchi.rentals.dto;

import com.mibiatchi.rentals.entity.Role;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {}