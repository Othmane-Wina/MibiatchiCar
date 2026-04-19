package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    // For your "search by name" Figma feature
    List<Car> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(String make, String model);

    // To only show cars that admins have made available
    List<Car> findByIsAvailableTrue();
}