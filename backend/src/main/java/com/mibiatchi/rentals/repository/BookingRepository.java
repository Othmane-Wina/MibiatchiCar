package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    // The query that prevents double-booking the same car
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.car.id = :carId " +
            "AND b.status IN ('CONFIRMED', 'PENDING') " +
            "AND (b.startDate <= :endDate AND b.endDate >= :startDate)")
    long countOverlappingBookings(@Param("carId") Long carId,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);
}