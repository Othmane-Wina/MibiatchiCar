package com.mibiatchi.rentals.controller;

import com.mibiatchi.rentals.entity.CarStatus;
import com.mibiatchi.rentals.dto.CarRequest;
import com.mibiatchi.rentals.dto.CarResponse;
import com.mibiatchi.rentals.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;

    // ==========================================
    // PUBLIC ENDPOINTS (No login required)
    // ==========================================

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllAvailableCars() {
        // Powers the main catalog on the Next.js homepage
        List<CarResponse> responses = carService.getAllAvailableCars();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        // Powers the dedicated "Car Details" page
        CarResponse response = carService.getCarById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(@RequestParam String keyword) {
        // Powers the search bar
        List<CarResponse> responses = carService.searchCars(keyword);
        return ResponseEntity.ok(responses);
    }

    // ==========================================
    // ADMIN ENDPOINTS (Fleet Management)
    // ==========================================

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest request) {
        CarResponse response = carService.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(
            @PathVariable Long id,
            @Valid @RequestBody CarRequest request) {
        CarResponse response = carService.updateCar(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CarResponse> updateCarStatus(
            @PathVariable Long id,
            @RequestParam CarStatus status) {
        // PATCH is used here because we are only updating a single field (the status), not the whole car
        CarResponse response = carService.updateCarStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> retireCar(@PathVariable Long id) {
        // Even though the service does a "Soft Delete" (changing status to RETIRED_SOLD),
        // using the @DeleteMapping annotation is the correct RESTful standard.
        carService.retireCar(id);
        return ResponseEntity.noContent().build(); // 204 No Content is standard for a successful delete
    }
}