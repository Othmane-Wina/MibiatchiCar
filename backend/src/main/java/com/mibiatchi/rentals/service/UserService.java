package com.mibiatchi.rentals.service;

import com.mibiatchi.rentals.entity.User;
import com.mibiatchi.rentals.dto.UserRequest;
import com.mibiatchi.rentals.dto.UserResponse;
import com.mibiatchi.rentals.mapper.UserMapper;
import com.mibiatchi.rentals.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return userMapper.toResponse(user);
    }

    public UserResponse createUser(UserRequest request) {
        // Business Rule: Emails must be unique
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email is already taken!");
        }

        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        // If they are changing their email, ensure the new email isn't already taken
        if (!existingUser.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email is already taken!");
        }

        existingUser.setFirstName(request.firstName());
        existingUser.setLastName(request.lastName());
        existingUser.setEmail(request.email());

        // Only update the password if a new one is provided in the request
        if (request.password() != null && !request.password().isBlank()) {
            existingUser.setPassword(request.password());
        }

        // Update the role if it was provided
        if (request.role() != null) {
            existingUser.setRole(request.role());
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}