package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Crucial for the Spring Security login we will build later
    Optional<User> findByEmail(String email);
}