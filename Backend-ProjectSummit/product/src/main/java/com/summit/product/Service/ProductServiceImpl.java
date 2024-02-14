package com.summit.product.Service;

import com.summit.product.config.ProductModelMapper;
import com.summit.product.constants.ErrorMessageConstants;
import com.summit.product.constants.MessageConstants;
import com.summit.product.dto.ProductDTO;
import com.summit.product.exception.CustomException;
import com.summit.product.model.Product;
import com.summit.product.repository.ProductRepository;
import com.summit.product.response.MessageObject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper ;
    
    private final ProductRepository productRepo;

    @Override
    public List<Product> getProducts(boolean onlyActiveProducts){
        //Return list of products
        //If error occurs return 500 status (Internal Server Error)
        try{
            if(onlyActiveProducts){
                // Need to check if product is active and also if it has been approved
                List<Product> activeProducts = productRepo.findAll().stream()
                        .filter(Product::isActive)
                        .filter(Product::isApproved)
                        .collect(Collectors.toList());
                return activeProducts;
            }else{
                List<Product> products = productRepo.findAll();
                return products;
            }
        }catch(Exception e){
            log.error("Error accessing the database while fetching all products.",e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Product getProductById(String productId) {
        Optional<Product> product;
        try {
            product = productRepo.findById(productId);
        }catch(Exception e){
            log.error("Error accessing database when fetching product with ID: {}",productId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(product.isPresent()) {
            if (!product.get().isActive()) {
                log.info("Requested product with ID: {} is not active", productId);
            }
            return product.get();
        }else{
            log.error("Requested product with ID: {} not found", productId);
            throw new CustomException(ErrorMessageConstants.ERROR_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public Product getActiveProductById(String productId) {
        Optional<Product> product;
        try {
            product = productRepo.findById(productId);
        }catch(Exception e){
            log.error("Error accessing database when fetching product with ID: {}",productId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(product.isPresent()) {
            if (!product.get().isActive()) {
                log.error("Require active Product. Requested product with ID: {} is not active", productId);
                throw new CustomException(ErrorMessageConstants.ERROR_PRODUCT_NOT_ACTIVE, HttpStatus.FOUND);
            }
            return product.get();
        }else{
            log.error("Requested product with ID: {} not found", productId);
            throw new CustomException(ErrorMessageConstants.ERROR_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Product> getProductsOfSupplier(String supplierId) {
        try{
//            throw new CustomException("Error: Unable to retrieve products", HttpStatus.INTERNAL_SERVER_ERROR);
            List<Product> supplierProducts = productRepo.findAll().stream()
                    .filter(product -> product.isActive() && product.getSupplierId().equals(supplierId))
                    .collect(Collectors.toList());
            return supplierProducts;
        }catch(Exception e){
            log.error("Error accessing database when fetching products of supplierId:"+supplierId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Product> getPendingProducts() {
        try{
            List<Product> pendingProducts = productRepo.findAll().stream()
                    .filter(product-> product.isActive() && !product.isApproved())
                    .collect(Collectors.toList());
            return pendingProducts;

        }catch(Exception e){
            log.error("Error accessing the database while fetching pending products",e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MessageObject createProduct(@Valid ProductDTO newProductDTO) {
        try{
            Product productToBeSaved = modelMapper.map(newProductDTO,Product.class);
            log.debug("The Mapped Product is: "+productToBeSaved.toString());
            productRepo.save(productToBeSaved);
            return new MessageObject(MessageConstants.CREATE_SUCCESS);
        }catch(Exception e) {
            log.error("Error accessing database when creating product {}",newProductDTO.toString(),e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Only to update status as approved
    @Override
    public MessageObject updateProduct(String productId) {
        Product product = getActiveProductById(productId);
        product.setApproved(true);
        product.setUpdatedDate(LocalDateTime.now());
        try{
            productRepo.save(product);
            return new MessageObject(MessageConstants.UPDATE_SUCCESS+": Approved :"+productId);
        }catch(Exception e){
            log.error("Error accessing database when approving product with ID: {}",productId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Only to update status to inactive
    @Override
    public MessageObject deactivateProduct(String productId) {
            Product product = getActiveProductById(productId);
            product.setActive(false);
            product.setUpdatedDate(LocalDateTime.now());
        try{
            productRepo.save(product);
            return new MessageObject(MessageConstants.DELETE_SUCCESS+": Deleted :"+productId);
        }catch(Exception e) {
            log.error("Error accessing database when removing product with ID: {}",productId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
