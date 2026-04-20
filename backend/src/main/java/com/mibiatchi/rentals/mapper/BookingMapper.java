package com.mibiatchi.rentals.mapper;

import com.mibiatchi.rentals.entity.Booking;
import com.mibiatchi.rentals.entity.BookingStatus;
import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.entity.User;
import com.mibiatchi.rentals.dto.BookingRequest;
import com.mibiatchi.rentals.dto.BookingResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BookingMapper {

    // 1. Entity -> Response (Outbound)
    public BookingResponse toBookingResponse(Booking booking) {
        if (booking == null) {
            return null;
        }

        String carMakeModel = booking.getCar().getMake() + " " + booking.getCar().getModel();

        return new BookingResponse(
                booking.getId(),
                booking.getCar().getId(),
                carMakeModel,
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getStatus().name(),
                booking.getTotalPrice()
        );
    }

    // 2. Request -> Entity (Inbound)
    public Booking toEntity(BookingRequest request, Car car, User user, BigDecimal calculatedPrice) {
        if (request == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setCar(car);
        booking.setUser(user);
        booking.setStartDate(request.startDate());
        booking.setEndDate(request.endDate());
        booking.setTotalPrice(calculatedPrice);
        booking.setStatus(BookingStatus.PENDING); // Default status for new bookings

        return booking;
    }
}