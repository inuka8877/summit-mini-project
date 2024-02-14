package com.summit.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name="product")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Column(name = "product_id", columnDefinition = "VARCHAR(255) DEFAULT nextval('product_id_sequence')")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String productId;
    @Column(name = "product_name")
    private String productName;

    private String description;
    private BigDecimal price;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    private String image;

    @Column(name = "is_approved",columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isApproved;

    @Column(name = "is_active",columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;
}
