package com.mibiatchi.rentals.service;

import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.entity.CarStatus;
import com.mibiatchi.rentals.dto.CarRequest;
import com.mibiatchi.rentals.dto.CarResponse;
import com.mibiatchi.rentals.mapper.CarMapper;
import com.mibiatchi.rentals.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    // ==========================================
    // PUBLIC ENDPOINTS (For the Next.js Frontend)
    // ==========================================

    @Transactional(readOnly = true)
    public List<CarResponse> getAllAvailableCars() {
        return carRepository.findByStatus(CarStatus.ACTIVE)
                .stream()
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));

        return carMapper.toCarResponse(car);
    }

    @Transactional(readOnly = true)
    public List<CarResponse> searchCars(String keyword) {
        return carRepository.findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(keyword, keyword)
                .stream()
                .filter(car -> car.getStatus() == CarStatus.ACTIVE) // Business Rule: Only show active cars in search
                .map(carMapper::toCarResponse)
                .collect(Collectors.toList());
    }

    // ==========================================
    // ADMIN FLEET MANAGEMENT METHODS
    // ==========================================

    @Transactional
    public CarResponse createCar(CarRequest request) {
        Car newCar = carMapper.toEntity(request);
        // The Mapper automatically sets the status to ACTIVE for new cars
        Car savedCar = carRepository.save(newCar);
        return carMapper.toCarResponse(savedCar);
    }

    @Transactional
    public CarResponse updateCar(Long id, CarRequest request) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));

        // Use the mapper to neatly apply the new values to the existing entity
        carMapper.updateEntityFromRequest(request, existingCar);

        Car updatedCar = carRepository.save(existingCar);
        return carMapper.toCarResponse(updatedCar);
    }

    @Transactional
    public CarResponse retireCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));

        // Soft Delete: Preserves financial/booking history while removing it from the active fleet
        car.setStatus(CarStatus.RETIRED_SOLD);

        Car savedCar = carRepository.save(car);
        return carMapper.toCarResponse(savedCar);
    }

    @Transactional
    public CarResponse updateCarStatus(Long id, CarStatus newStatus) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));

        // Useful for Admins placing a car into MAINTENANCE
        car.setStatus(newStatus);

        Car savedCar = carRepository.save(car);
        return carMapper.toCarResponse(savedCar);
    }
}