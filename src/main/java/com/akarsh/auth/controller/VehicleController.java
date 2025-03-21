package com.akarsh.auth.controller;

import com.akarsh.auth.dto.VehicleDto;
import com.akarsh.auth.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vendor/{vendorId}")
    public ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Long vendorId) {
        // Pass the vendor ID directly to the service
        VehicleDto savedVehicle = vehicleService.addVehicle(vehicleDto, vendorId);
        return ResponseEntity.ok(savedVehicle);
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByVendor(@PathVariable Long vendorId) {
        List<VehicleDto> vehicles = vehicleService.getVehiclesByVendor(vendorId);
        return ResponseEntity.ok(vehicles);
    }
}
