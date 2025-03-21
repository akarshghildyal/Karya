package com.akarsh.auth.dto;

import lombok.Data;

@Data
public class VehicleDto {
    private Long id;
    private String registrationNumber;
    private String model;
    private Integer seatingCapacity;
    private Long vendorId;
    private Long assignedDriverId;

    
}
