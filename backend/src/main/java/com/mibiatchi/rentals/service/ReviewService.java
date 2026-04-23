package com.mibiatchi.rentals.service;

import com.mibiatchi.rentals.entity.BookingStatus;
import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.entity.Review;
import com.mibiatchi.rentals.entity.User;
import com.mibiatchi.rentals.dto.ReviewRequest;
import com.mibiatchi.rentals.dto.ReviewResponse;
import com.mibiatchi.rentals.mapper.ReviewMapper;
import com.mibiatchi.rentals.repository.BookingRepository;
import com.mibiatchi.rentals.repository.CarRepository;
import com.mibiatchi.rentals.repository.ReviewRepository;
import com.mibiatchi.rentals.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponse addReview(Long userId, ReviewRequest request) {
        // 1. Verify the User and Car exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Car car = carRepository.findById(request.carId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        // 2. The "Verified Renter" Security Check
        boolean hasCompletedTrip = bookingRepository.existsByUserIdAndCarIdAndStatus(
                userId, request.carId(), BookingStatus.COMPLETED
        );

        if (!hasCompletedTrip) {
            throw new IllegalStateException("You can only review cars you have successfully rented and returned.");
        }

        // 3. Save the Review
        Review review = reviewMapper.toEntity(request, car, user);
        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toReviewResponse(savedReview);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getCarReviews(Long carId) {
        // Anyone can read reviews, so no security checks needed here
        return reviewRepository.findByCarId(carId)
                .stream()
                .map(reviewMapper::toReviewResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewResponse updateReview(Long reviewId, Long userId, ReviewRequest request) {
        // 1. Fetch the existing review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        // 2. Security: Ensure the user trying to update it is the actual author
        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission to modify this review.");
        }

        // 3. Apply updates and save
        reviewMapper.updateEntityFromRequest(request, review);
        Review updatedReview = reviewRepository.save(review);

        // 4. Return the updated data to the frontend
        return reviewMapper.toReviewResponse(updatedReview);
    }

    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        // Security: Users can only delete their own reviews
        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You do not have permission to delete this review.");
        }

        reviewRepository.delete(review);
    }
}