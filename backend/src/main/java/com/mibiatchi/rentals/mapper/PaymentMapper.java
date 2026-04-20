package com.mibiatchi.rentals.mapper;

import com.mibiatchi.rentals.entity.Booking;
import com.mibiatchi.rentals.entity.Payment;
import com.mibiatchi.rentals.entity.PaymentStatus;
import com.mibiatchi.rentals.dto.PaymentRequest;
import com.mibiatchi.rentals.dto.PaymentResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    // 1. Entity -> Response (Outbound to the Next.js Receipt Page)
    public PaymentResponse toPaymentResponse(Payment payment) {
        if (payment == null) {
            return null;
        }

        return new PaymentResponse(
                payment.getId(),
                payment.getBooking().getId(),
                payment.getAmount(),
                payment.getStatus().name(),
                payment.getTransactionId(),
                payment.getPaymentDate()
        );
    }

    // 2. Request + Business Results -> Entity (Inbound to PostgreSQL)
    public Payment toEntity(PaymentRequest request, Booking booking, BigDecimal amount, String transactionId, PaymentStatus status) {
        if (request == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount); // Pulled from the Booking, not trusted from the frontend!
        payment.setTransactionId(transactionId); // Returned by Stripe/PayPal
        payment.setStatus(status);
        payment.setPaymentDate(LocalDateTime.now());

        return payment;
    }
}