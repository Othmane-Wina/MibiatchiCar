package com.mibiatchi.rentals.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    private Integer productionYear;

    @Column(nullable = false)
    private BigDecimal dailyRate;

    private String imageUrl; // For the Figma 'car pic'

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status = CarStatus.ACTIVE; // Default state for new cars

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
