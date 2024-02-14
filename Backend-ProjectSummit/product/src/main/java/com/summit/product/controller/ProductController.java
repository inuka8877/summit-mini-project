package com.summit.product.controller;

import com.summit.product.Service.ProductService;
import com.summit.product.dto.ProductDTO;
import com.summit.product.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/products")
@Slf4j
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getProducts(@RequestParam(name = "pending", defaultValue = "false") boolean onlyPending){
        ResponseObject responseObject;
        if (onlyPending) {
            responseObject = new ResponseObject(productService.getPendingProducts(), HttpStatus.OK);
        } else {
            responseObject = new ResponseObject(productService.getProducts(true), HttpStatus.OK);
        }

        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    @GetMapping(path = "/supplier/{supplierId}")
    public ResponseEntity<ResponseObject> getProductsOfSupplier(
            @PathVariable String supplierId
    ){
        ResponseObject responseObject = new ResponseObject(productService.getProductsOfSupplier(supplierId), HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ResponseObject> getProductById(
            @PathVariable String productId
    ){
        ResponseObject responseObject = new ResponseObject(productService.getProductById(productId), HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    @PatchMapping(path="/{productId}")
    public ResponseEntity<ResponseObject> updateProductById(
            @PathVariable String productId
    ){
        ResponseObject responseObject = new ResponseObject(productService.updateProduct(productId), HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createProduct(
            @Valid @RequestBody ProductDTO product
    ){
        log.debug("Product Obtained: ",product.toString());
        ResponseObject responseObject = new ResponseObject(productService.createProduct(product), HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    @DeleteMapping(path="{productId}")
    public ResponseEntity<ResponseObject> deleteProduct(
            @PathVariable String productId
    ){
        ResponseObject responseObject = new ResponseObject(productService.deactivateProduct(productId),HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

}
