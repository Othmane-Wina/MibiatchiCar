package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.entity.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    // For your "search by name" Figma feature
    List<Car> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(String make, String model);

    // To only show cars that admins have made available
    List<Car> findByStatus(CarStatus status);

    @Modifying
    @Query("UPDATE Car c SET c.status = 'ACTIVE' WHERE c.status = 'RENTED' " +
            "AND NOT EXISTS (SELECT 1 FROM Booking b WHERE b.car = c " +
            "AND b.status IN ('CONFIRMED', 'PENDING') " +
            "AND b.endDate >= CURRENT_DATE)")
    int releaseExpiredRentedCars();
}