package com.akarsh.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Entity
@Table(name = "vendors")
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @ManyToOne
    @JoinColumn(name = "parent_vendor_id")
    private Vendor parentVendor;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
