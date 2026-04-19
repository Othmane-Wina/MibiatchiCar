package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCarId(Long carId);
}