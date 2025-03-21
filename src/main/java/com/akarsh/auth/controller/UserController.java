package com.akarsh.auth.controller;

import com.akarsh.auth.dto.AuthResponse;
import com.akarsh.auth.dto.LoginDto;
import com.akarsh.auth.dto.SubVendorAssignmentDto;
import com.akarsh.auth.dto.UserDto;
import com.akarsh.auth.dto.UserRegistrationDto;
import com.akarsh.auth.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        try {
            UserDto userDto = userService.registerUser(registrationDto);
            AuthResponse response = new AuthResponse("User registered successfully", userDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginDto loginDto) {
        try {
            UserDto userDto = userService.loginUser(loginDto);
            AuthResponse response = new AuthResponse("Login successful", userDto);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials exception: " + e.getMessage());
            AuthResponse response = new AuthResponse("Invalid email or password", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            System.out.println("Unexpected exception during login: " + e.getMessage());
            e.printStackTrace();
            AuthResponse response = new AuthResponse("An error occurred during login: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/assign-subvendor")
    public ResponseEntity<AuthResponse> assignSubVendor(@Valid @RequestBody SubVendorAssignmentDto assignmentDto) {
        try {
            UserDto updatedSubVendor = userService.assignSubVendor(assignmentDto);
            AuthResponse response = new AuthResponse("Sub-vendor assigned successfully", updatedSubVendor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/subvendors/{vendorId}")
    public ResponseEntity<List<UserDto>> getSubVendors(@PathVariable Long vendorId) {
        try {
            List<UserDto> subVendors = userService.getSubVendors(vendorId);
            return ResponseEntity.ok(subVendors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }    
}
