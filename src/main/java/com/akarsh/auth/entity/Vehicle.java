package com.akarsh.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String model;

    @Column(name = "seating_capacity")
    private Integer seatingCapacity;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(name = "assigned_driver_id")
    private Long assignedDriverId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
