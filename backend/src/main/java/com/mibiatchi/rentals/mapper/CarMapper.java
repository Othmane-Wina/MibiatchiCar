package com.mibiatchi.rentals.mapper;

import com.mibiatchi.rentals.dto.CarRequest;
import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.dto.CarResponse;
import com.mibiatchi.rentals.entity.CarStatus;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarResponse toCarResponse(Car car) {
        if (car == null) {
            return null;
        }

        return new CarResponse(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getProductionYear(),
                car.getDailyRate(),
                car.getImageUrl()
        );
    }

    // 2. Request -> Entity (For Creating a NEW Car)
    public Car toEntity(CarRequest request) {
        if (request == null) {
            return null;
        }

        Car car = new Car();
        car.setMake(request.make());
        car.setModel(request.model());
        car.setProductionYear(request.productionYear());
        car.setDailyRate(request.dailyRate());
        car.setImageUrl(request.imageUrl());
        car.setStatus(CarStatus.ACTIVE);

        return car;
    }

    // 3. Request + Existing Entity -> Updated Entity (For Modifying an EXISTING Car)
    public void updateEntityFromRequest(CarRequest request, Car car) {
        if (request == null || car == null) {
            return;
        }

        car.setMake(request.make());
        car.setModel(request.model());
        car.setProductionYear(request.productionYear());
        car.setDailyRate(request.dailyRate());
        if (request.imageUrl() != null) {
            car.setImageUrl(request.imageUrl());
        }
    }
}