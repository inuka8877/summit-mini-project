package com.summit.order.config;

import com.summit.order.dto.OrderDTO;
import com.summit.order.model.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class OrderModelMapper {
    @Bean
    public ModelMapper orderMapper(){
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.addMappings(new PropertyMap<OrderDTO, Order>() {
            @Override
            protected void configure() {
                skip(destination.getOrderId());

                map().setCreatedDate(LocalDateTime.now());
                map().setUpdatedDate(LocalDateTime.now());
                map().setCompleted(false);
                map().setActive(true);
                using(ctx -> {
                    OrderDTO source = (OrderDTO) ctx.getSource();
                    BigDecimal unitPrice = source.getUnitPrice();
                    int quantity = source.getQuantity();

                    if (unitPrice != null && quantity > 0) {
                        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
                        return totalPrice;
                    } else {
                        return null; // Handle invalid input, e.g., throw an exception or set default value
                    }
                }).map(source, destination.getTotalPrice());

            }
        });
        return modelMapper;
    }
}
