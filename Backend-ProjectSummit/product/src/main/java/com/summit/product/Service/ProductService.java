package com.summit.product.Service;

import com.summit.product.dto.ProductDTO;
import com.summit.product.model.Product;
import com.summit.product.response.MessageObject;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    //Get All Products. Only if active
    List<Product> getProducts(boolean onlyActiveProducts);
    //Get a single product
    Product getProductById(String productId);
    //Get the product catalog of a certain supplier
    List<Product> getProductsOfSupplier(String supplierId);
    //Get the products that are pending approval
    List<Product> getPendingProducts();
    //Create new product
    MessageObject createProduct(@Valid ProductDTO newProduct);
    //Update product
    MessageObject updateProduct(String productId);
    //Remove product
    MessageObject deactivateProduct(String productId);
}
