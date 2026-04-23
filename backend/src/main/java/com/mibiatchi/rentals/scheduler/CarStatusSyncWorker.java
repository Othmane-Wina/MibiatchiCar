package com.mibiatchi.rentals.scheduler;

import com.mibiatchi.rentals.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CarStatusSyncWorker {

    private static final Logger log = LoggerFactory.getLogger(CarStatusSyncWorker.class);
    private final CarRepository carRepository;

    /**
     * Runs automatically every 15 minutes.
     * It is completely idempotent (safe to run repeatedly).
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    @Transactional
    public void syncRentedCars() {
        int releasedCount = carRepository.releaseExpiredRentedCars();

        if (releasedCount > 0) {
            log.info("Self-Healing Sync: Successfully released {} expired rental cars back to ACTIVE status.", releasedCount);
        }
    }
}