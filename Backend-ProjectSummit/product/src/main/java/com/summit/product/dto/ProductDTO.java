package com.summit.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull(message="Product name missing")
    @NotEmpty(message="Product name cannot be empty")
    private String productName;

    @NotNull(message="Product description missing")
    @NotEmpty(message="Product descripton cannot be empty")
    private String description;

    @NotNull(message = "Product price missing")
    @DecimalMin(value="0", message = "Price needs to be positive")
    private BigDecimal price;

    @NotNull(message = "Product supplierId missing")
    @NotEmpty(message="Product supplierId cannot be empty")
    private String supplierId;

    @NotNull(message="Product supplierName missing")
    @NotEmpty(message="Product supplierName cannot be empty")
    private String supplierName;

    @URL(message="Not a valid image URL")
    @NotEmpty(message = "Image not received.")
    private String image;
}
