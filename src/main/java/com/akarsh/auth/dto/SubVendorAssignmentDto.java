package com.akarsh.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubVendorAssignmentDto {
    @NotNull(message = "Sub-vendor ID is required")
    private Long subVendorId;
    
    @NotNull(message = "Parent vendor ID is required")
    private Long parentVendorId;
}
