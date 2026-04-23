package com.mibiatchi.rentals.controller;

import com.mibiatchi.rentals.dto.ReviewRequest;
import com.mibiatchi.rentals.dto.ReviewResponse;
import com.mibiatchi.rentals.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ReviewService reviewService;

    // ==========================================
    // PUBLIC ENDPOINTS (No login required)
    // ==========================================

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<ReviewResponse>> getCarReviews(@PathVariable Long carId) {
        // This is public! Anyone browsing the Next.js app should be able to read the reviews.
        List<ReviewResponse> responses = reviewService.getCarReviews(carId);
        return ResponseEntity.ok(responses);
    }

    // ==========================================
    // CUSTOMER ENDPOINTS (Login required)
    // ==========================================

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(
            @RequestParam Long userId,
            @Valid @RequestBody ReviewRequest request) {
        // The Service layer handles the "Did this user actually rent this car?" check.
        ReviewResponse response = reviewService.addReview(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestParam Long userId,
            @Valid @RequestBody ReviewRequest request) {
        // The Service layer ensures the user is the actual author of the review.
        ReviewResponse response = reviewService.updateReview(reviewId, userId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.noContent().build();
    }
}