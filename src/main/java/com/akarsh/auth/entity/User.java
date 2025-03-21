package com.akarsh.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @Column
    private String role = "Vendor";
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
     
    @ManyToOne
    @JoinColumn(name = "parent_vendor_id")
    private User parentVendor;

    @OneToMany(mappedBy = "parentVendor")
    private List<User> subVendors = new ArrayList<>();
}
