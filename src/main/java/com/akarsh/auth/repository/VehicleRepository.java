package com.akarsh.auth.repository;

import com.akarsh.auth.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByVendorId(Long vendorId);
}
