package com.summit.product.config;

import com.summit.product.dto.ProductDTO;
import com.summit.product.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Configuration
public class ProductModelMapper {
    @Bean
    public ModelMapper productMapper(){
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ProductDTO, Product>() {
            @Override
            protected void configure() {
                skip(destination.getProductId());
                map().setCreatedDate(LocalDateTime.now());
                map().setUpdatedDate(LocalDateTime.now());
                map().setApproved(false);
                map().setActive(true);

            }
        });
        return modelMapper;
    }
}
