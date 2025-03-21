package com.akarsh.auth.service;

import com.akarsh.auth.dto.LoginDto;
import com.akarsh.auth.dto.SubVendorAssignmentDto;
import com.akarsh.auth.dto.UserDto;
import com.akarsh.auth.dto.UserRegistrationDto;
import com.akarsh.auth.entity.User;
import com.akarsh.auth.entity.Vendor;
import com.akarsh.auth.repository.UserRepository;
import com.akarsh.auth.repository.VendorRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VendorRepository vendorRepository; // Add this field
    
    @Autowired
    public UserService(UserRepository userRepository, 
                      PasswordEncoder passwordEncoder,
                      VendorRepository vendorRepository) { // Add parameter
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.vendorRepository = vendorRepository; // Initialize field
    }
    
    @Transactional
    public UserDto registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        
        User user = new User();
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));
        
        if (registrationDto.getRole() != null) {
            user.setRole(registrationDto.getRole());
        }
        
        User savedUser = userRepository.save(user);
        
        // If the user is a vendor, create a corresponding vendor record with the same ID
        if ("Vendor".equals(savedUser.getRole())) {
            createVendorForUser(savedUser);
        }
        
        return convertToDto(savedUser);
    }


    public UserDto loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        
        if (user == null) {
            throw new BadCredentialsException("Invalid email or password");
        }
        
        // Debug password verification
        String providedPassword = loginDto.getPassword();
        String storedHash = user.getPasswordHash();
        System.out.println("Provided password: " + providedPassword);
        System.out.println("Stored hash: " + storedHash);
        System.out.println("Password match result: " + passwordEncoder.matches(providedPassword, storedHash));
        
        if (!passwordEncoder.matches(providedPassword, storedHash)) {
            throw new BadCredentialsException("Invalid email or password");
        }
        
        return convertToDto(user);
    }
    
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    @Transactional
    public UserDto assignSubVendor(SubVendorAssignmentDto assignmentDto) {
        User subVendor = userRepository.findById(assignmentDto.getSubVendorId())
            .orElseThrow(() -> new RuntimeException("Sub-vendor not found"));
        User parentVendor = userRepository.findById(assignmentDto.getParentVendorId())
            .orElseThrow(() -> new RuntimeException("Parent vendor not found"));

        if (!"Vendor".equals(parentVendor.getRole())) {
            throw new RuntimeException("Parent must be a vendor");
        }

        subVendor.setParentVendor(parentVendor);
        User updatedSubVendor = userRepository.save(subVendor);
        return convertToDto(updatedSubVendor);
    }

    public List<UserDto> getSubVendors(Long vendorId) {
        User vendor = userRepository.findById(vendorId)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
        return vendor.getSubVendors().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

   

    @Transactional
    private void createVendorForUser(User user) {
        // Check if a vendor record already exists for this user
        if (vendorRepository.existsByCreatedBy(user)) {
            return;
        }
        
        Vendor vendor = new Vendor();
        vendor.setName(user.getName());
        vendor.setEmail(user.getEmail());
        vendor.setPhone("PHONE-" + System.currentTimeMillis()); // Generate a unique phone number
        vendor.setCreatedBy(user);
        
        // Set the vendor ID to match the user ID
        // This requires a custom repository method or native query
        vendorRepository.saveWithId(vendor, user.getId());
    }

}
