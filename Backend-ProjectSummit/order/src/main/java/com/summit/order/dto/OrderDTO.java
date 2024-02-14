package com.summit.order.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotNull(message="Product id missing")
    @NotEmpty(message="Product id cannot be empty")
    private String productId;

    @NotNull(message="Product name missing")
    @NotEmpty(message="Product name cannot be empty")
    private String productName;

    @NotNull(message = "Product quantity missing")
    @DecimalMin(value="0", message = "Quantity needs to be positive")
    private int quantity;

    @NotNull(message = "Product price missing")
    @DecimalMin(value="0", message = "Price needs to be positive")
    private BigDecimal unitPrice;

    @NotNull(message = "Product supplierId missing")
    @NotEmpty(message="Product supplierId cannot be empty")
    private String supplierId;

    @NotNull(message="Product supplierName missing")
    @NotEmpty(message="Product supplierName cannot be empty")
    private String supplierName;

    @NotNull(message = "Order consumerId missing")
    @NotEmpty(message="Order consumerId cannot be empty")
    private String consumerId;

    @NotNull(message="Order consumerName missing")
    @NotEmpty(message="Order consumerName cannot be empty")
    private String consumerName;

    @NotNull(message="DeliveryAddress missing")
    @NotEmpty(message = "Delivery Address not received.")
    private String deliveryAddress;
}
