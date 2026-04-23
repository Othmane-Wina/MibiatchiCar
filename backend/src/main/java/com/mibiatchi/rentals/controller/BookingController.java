package com.mibiatchi.rentals.controller;

import com.mibiatchi.rentals.dto.BookingRequest;
import com.mibiatchi.rentals.dto.BookingResponse;
import com.mibiatchi.rentals.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Allows your Next.js app to talk to this API
public class BookingController {

    private final BookingService bookingService;

    // ==========================================
    // CUSTOMER ENDPOINTS
    // ==========================================

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponse>> getUserBookings(@RequestParam Long userId) {
        List<BookingResponse> responses = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long bookingId,
            @RequestParam Long userId) {
        BookingResponse response = bookingService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bookingId}/dates")
    public ResponseEntity<BookingResponse> updateBookingDates(
            @PathVariable Long bookingId,
            @RequestParam Long userId,
            @RequestParam LocalDate newStart,
            @RequestParam LocalDate newEnd) {
        BookingResponse response = bookingService.updateBookingDates(bookingId, userId, newStart, newEnd);
        return ResponseEntity.ok(response);
    }

    // ==========================================
    // ADMIN ENDPOINTS
    // ==========================================

    @GetMapping
    public ResponseEntity<Page<BookingResponse>> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BookingResponse> responses = bookingService.getAllBookings(page, size);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{bookingId}/swap-car")
    public ResponseEntity<BookingResponse> adminSwapCar(
            @PathVariable Long bookingId,
            @RequestParam Long newCarId) {
        BookingResponse response = bookingService.adminSwapCar(bookingId, newCarId);
        return ResponseEntity.ok(response);
    }
}