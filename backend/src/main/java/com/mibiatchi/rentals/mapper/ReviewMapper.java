package com.mibiatchi.rentals.mapper;

import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.entity.Review;
import com.mibiatchi.rentals.entity.User;
import com.mibiatchi.rentals.dto.ReviewRequest;
import com.mibiatchi.rentals.dto.ReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    // 1. Entity -> Response (Outbound to the Next.js Car Details page)
    public ReviewResponse toReviewResponse(Review review) {
        if (review == null) {
            return null;
        }

        // Combine first and last name for a clean display on the frontend
        String reviewerName = review.getUser().getFirstName() + " " + review.getUser().getLastName();

        return new ReviewResponse(
                review.getId(),
                reviewerName,
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }

    // 2. Request + Database Entities -> Entity (Inbound to PostgreSQL)
    public Review toEntity(ReviewRequest request, Car car, User user) {
        if (request == null) {
            return null;
        }

        Review review = new Review();
        review.setCar(car);
        review.setUser(user);
        review.setRating(request.rating());
        review.setComment(request.comment());

        // Notice we do NOT set the `createdAt` timestamp here!
        // The @PrePersist annotation we added to your Review entity earlier
        // will handle that automatically the exact millisecond it saves to the database.

        return review;
    }

    // 3. Request + Existing Entity -> Updated Entity (For Modifying an EXISTING Review)
    public void updateEntityFromRequest(ReviewRequest request, Review review) {
        if (request == null || review == null) {
            return;
        }

        review.setRating(request.rating());
        review.setComment(request.comment());
    }
}