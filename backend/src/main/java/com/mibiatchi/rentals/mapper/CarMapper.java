package com.mibiatchi.rentals.mapper;

import com.mibiatchi.rentals.entity.Car;
import com.mibiatchi.rentals.dto.CarResponse;
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
}