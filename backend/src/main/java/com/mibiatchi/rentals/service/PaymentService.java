package com.mibiatchi.rentals.service;

import com.mibiatchi.rentals.entity.Booking;
import com.mibiatchi.rentals.entity.BookingStatus;
import com.mibiatchi.rentals.entity.Payment;
import com.mibiatchi.rentals.entity.PaymentStatus;
import com.mibiatchi.rentals.dto.PaymentRequest;
import com.mibiatchi.rentals.dto.PaymentResponse;
import com.mibiatchi.rentals.mapper.PaymentMapper;
import com.mibiatchi.rentals.repository.BookingRepository;
import com.mibiatchi.rentals.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponse processPayment(Long userId, PaymentRequest request) {
        // 1. Fetch and Validate the Booking
        Booking booking = bookingRepository.findById(request.bookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found."));

        // Security: Ensure the user paying actually owns the booking
        if (!booking.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission to pay for this booking.");
        }

        // Business Rule: Cannot pay for a cancelled or already confirmed booking
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Cannot process payment for a cancelled booking.");
        }
        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            throw new IllegalStateException("This booking has already been paid for.");
        }

        // 2. Process with the Payment Gateway (Mocked)
        PaymentResult gatewayResult = simulateStripeCharge(request.paymentToken(), booking.getTotalPrice());

        // 3. Build the Audit Record (The Payment Entity)
        Payment payment = paymentMapper.toEntity(
                request,
                booking,
                booking.getTotalPrice(),
                gatewayResult.transactionId(),
                gatewayResult.status()
        );

        Payment savedPayment = paymentRepository.save(payment);

        // 4. Update Booking Status ONLY if payment succeeds
        if (gatewayResult.status() == PaymentStatus.SUCCESS) {
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
        } else {
            // Throw an exception so the frontend knows to show a red error message
            throw new IllegalStateException("Payment failed: Your card was declined.");
        }

        return paymentMapper.toPaymentResponse(savedPayment);
    }

    @Transactional(readOnly = true)
    public PaymentResponse getPaymentReceipt(Long bookingId, Long userId) {
        Payment payment = paymentRepository.findByBookingId(bookingId);

        if (payment == null) {
            throw new IllegalArgumentException("No payment found for this booking.");
        }

        if (!payment.getBooking().getUser().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission to view this receipt.");
        }

        return paymentMapper.toPaymentResponse(payment);
    }

    // ==========================================
    // MOCK PAYMENT GATEWAY (Simulates Stripe/PayPal)
    // ==========================================

    private PaymentResult simulateStripeCharge(String token, BigDecimal amount) {
        // In real life, this method would send an HTTP request to Stripe's servers.
        // For testing, we look at the token string. If the Next.js frontend sends "tok_fail", we simulate a declined card.

        if ("tok_fail".equals(token)) {
            return new PaymentResult(null, PaymentStatus.FAILED);
        }

        // Success scenario: Generate a fake Stripe transaction ID (e.g., "ch_1N3k...")
        String fakeTransactionId = "ch_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        return new PaymentResult(fakeTransactionId, PaymentStatus.SUCCESS);
    }

    // A small internal record just to pass the mock data back cleanly
    private record PaymentResult(String transactionId, PaymentStatus status) {}
}