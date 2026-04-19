package com.mibiatchi.rentals.repository;

import com.mibiatchi.rentals.entity.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
    // Your future Python AI will need this to fetch a user's history
    List<UserInteraction> findByUserIdOrderByTimestampDesc(Long userId);
}