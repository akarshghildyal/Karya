package com.akarsh.auth.service;

import com.akarsh.auth.dto.VehicleDto;
import com.akarsh.auth.entity.User;
import com.akarsh.auth.entity.Vehicle;
import com.akarsh.auth.entity.Vendor;
import com.akarsh.auth.repository.UserRepository;
import com.akarsh.auth.repository.VehicleRepository;
import com.akarsh.auth.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository, VendorRepository vendorRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public VehicleDto addVehicle(VehicleDto vehicleDto, Long vendorId) {
        Vendor vendor;
        
        if (!vendorRepository.existsById(vendorId)) {
            // Check if user exists with this ID
            User user = userRepository.findById(vendorId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + vendorId));
            
            // Check if a vendor already exists with this email
            vendor = vendorRepository.findByEmail(user.getEmail());
            
            if (vendor == null) {
                // Create a new vendor record only if one doesn't already exist with this email
                Vendor newVendor = new Vendor();
                newVendor.setName(user.getName());
                newVendor.setEmail(user.getEmail());
                newVendor.setPhone("PHONE-" + System.currentTimeMillis());
                newVendor.setCreatedBy(user);
                
                vendor = vendorRepository.save(newVendor);
            }
        } else {
            vendor = vendorRepository.findById(vendorId)
                    .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));
        }
    
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
        vehicle.setVendor(vendor);
        
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToDto(savedVehicle);
    }





    
    public List<VehicleDto> getVehiclesByVendor(Long vendorId) {
        return vehicleRepository.findByVendorId(vendorId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private VehicleDto convertToDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        dto.setModel(vehicle.getModel());
        dto.setSeatingCapacity(vehicle.getSeatingCapacity());
        dto.setVendorId(vehicle.getVendor().getId());
        dto.setAssignedDriverId(vehicle.getAssignedDriverId());
        return dto;
    }

    
}
