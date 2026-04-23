package com.mibiatchi.rentals.service;

import com.mibiatchi.rentals.entity.Booking;
import com.mibiatchi.rentals.entity.BookingStatus;
import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.entity.User;
import com.mibiatchi.rentals.dto.BookingRequest;
import com.mibiatchi.rentals.dto.BookingResponse;
import com.mibiatchi.rentals.mapper.BookingMapper;
import com.mibiatchi.rentals.repository.BookingRepository;
import com.mibiatchi.rentals.repository.CarRepository;
import com.mibiatchi.rentals.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        // 1. Time Travel Check: Ensure end date is strictly after start date
        if (request.endDate().isBefore(request.startDate()) || request.endDate().isEqual(request.startDate())) {
            throw new IllegalArgumentException("End date must be at least one day after the start date.");
        }

        // 2. Fetch Entities (Throws error if someone sends a fake ID)
        Car car = carRepository.findById(request.carId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + request.carId()));

        if (!car.isAvailable()) {
            throw new IllegalStateException("This car is currently deactivated by the admin.");
        }

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + request.userId()));

        // 3. The Instant Booking Core: Check for Overlaps
        long overlappingBookings = bookingRepository.countOverlappingBookings(
                car.getId(), request.startDate(), request.endDate()
        );

        if (overlappingBookings > 0) {
            throw new IllegalStateException("Sorry, this car is already booked for the selected dates.");
        }

        // 4. The Accountant: Calculate Total Price
        long totalDays = ChronoUnit.DAYS.between(request.startDate(), request.endDate());
        BigDecimal calculatedPrice = car.getDailyRate().multiply(BigDecimal.valueOf(totalDays));

        // 5. Build and Save
        Booking newBooking = bookingMapper.toEntity(request, car, user, calculatedPrice);
        Booking savedBooking = bookingRepository.save(newBooking);

        // 6. Return the safe DTO to the frontend
        return bookingMapper.toBookingResponse(savedBooking);
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(bookingMapper::toBookingResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponse cancelBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        // Security check: Only the owner can cancel it
        if (!booking.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission to cancel this booking.");
        }

        // Business rule: Can't cancel if it already started
        if (booking.getStartDate().isBefore(LocalDate.now()) || booking.getStartDate().isEqual(LocalDate.now())) {
            throw new IllegalStateException("Cannot cancel a booking that has already started.");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(savedBooking);
    }

    @Transactional
    public BookingResponse updateBookingDates(Long bookingId, Long userId, LocalDate newStart, LocalDate newEnd) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (!booking.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission to modify this booking.");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Cannot modify a cancelled booking.");
        }

        if (newEnd.isBefore(newStart) || newEnd.isEqual(newStart)) {
            throw new IllegalArgumentException("End date must be at least one day after the start date.");
        }

        // Check availability using the new query that ignores THIS booking
        long overlappingBookings = bookingRepository.countOverlappingForUpdate(
                booking.getCar().getId(), newStart, newEnd, booking.getId()
        );

        if (overlappingBookings > 0) {
            throw new IllegalStateException("Sorry, the car is not available for these new dates.");
        }

        // Recalculate the price based on the new dates
        long totalDays = ChronoUnit.DAYS.between(newStart, newEnd);
        BigDecimal newPrice = booking.getCar().getDailyRate().multiply(BigDecimal.valueOf(totalDays));

        // Apply updates
        booking.setStartDate(newStart);
        booking.setEndDate(newEnd);
        booking.setTotalPrice(newPrice);

        Booking updatedBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(updatedBooking);
    }
}