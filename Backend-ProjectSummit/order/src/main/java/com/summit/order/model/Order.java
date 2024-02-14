package com.summit.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name="product_order")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Column(name = "order_id", columnDefinition = "VARCHAR(255) DEFAULT nextval('order_id_sequence')")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String orderId;

    @Column(name="product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    private int quantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "consumer_id")
    private String consumerId;

    @Column(name = "consumer_name")
    private String consumerName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "is_completed",columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isCompleted;

    @Column(name = "is_active",columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;
}