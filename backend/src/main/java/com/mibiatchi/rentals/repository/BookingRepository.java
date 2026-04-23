package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.Booking;
import com.mibiatchi.rentals.entity.BookingStatus;
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

    // Use this for updates to avoid overlapping with itself!
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.car.id = :carId " +
            "AND b.id != :excludeBookingId " +
            "AND b.status IN ('CONFIRMED', 'PENDING') " +
            "AND (b.startDate <= :endDate AND b.endDate >= :startDate)")
    long countOverlappingForUpdate(@Param("carId") Long carId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   @Param("excludeBookingId") Long excludeBookingId);


    // Checks if the user has actually rented this specific car and finished the trip
    boolean existsByUserIdAndCarIdAndStatus(Long userId, Long carId, BookingStatus status);
}