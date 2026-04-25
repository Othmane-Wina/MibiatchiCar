package com.mibiatchi.rentals.mapper;

import com.mibiatchi.rentals.entity.User; // Make sure this matches your exact entity package
import com.mibiatchi.rentals.dto.UserRequest;
import com.mibiatchi.rentals.dto.UserResponse;
import com.mibiatchi.rentals.entity.Role; // Importing your newly used Enum!
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());

        // The updated Enum logic!
        if (request.role() == null) {
            user.setRole(Role.CUSTOMER);
        } else {
            user.setRole(request.role());
        }

        return user;
    }

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}