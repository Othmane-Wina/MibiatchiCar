package com.mibiatchi.rentals.controller;

import com.mibiatchi.rentals.dto.PaymentRequest;
import com.mibiatchi.rentals.dto.PaymentResponse;
import com.mibiatchi.rentals.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;

    // ==========================================
    // CHECKOUT ENDPOINT
    // ==========================================

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestParam Long userId,
            @Valid @RequestBody PaymentRequest request) {

        // The service handles the mock Stripe logic and double-charge protection
        PaymentResponse response = paymentService.processPayment(userId, request);

        // Return 201 CREATED to tell the frontend the transaction was permanently recorded
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ==========================================
    // RECEIPT ENDPOINT
    // ==========================================

    @GetMapping("/receipt/{bookingId}")
    public ResponseEntity<PaymentResponse> getPaymentReceipt(
            @PathVariable Long bookingId,
            @RequestParam Long userId) {

        // The service ensures that only the owner of the booking can fetch this receipt
        PaymentResponse response = paymentService.getPaymentReceipt(bookingId, userId);

        return ResponseEntity.ok(response);
    }
}