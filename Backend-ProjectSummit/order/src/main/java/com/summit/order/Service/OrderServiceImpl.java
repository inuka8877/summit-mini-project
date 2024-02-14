package com.summit.order.Service;

import com.summit.order.constants.ErrorMessageConstants;
import com.summit.order.constants.MessageConstants;
import com.summit.order.dto.OrderDTO;
import com.summit.order.exception.CustomException;
import com.summit.order.model.Order;
import com.summit.order.repository.OrderRepository;
import com.summit.order.response.MessageObject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final ModelMapper modelMapper ;

    private final OrderRepository orderRepository;
    @Override
    public List<Order> getOrders(boolean onlyActiveOrders) {
        try{
            if(onlyActiveOrders){
                // Need to check if product is active and also if it has been approved
                return orderRepository.findAll().stream()
                        .filter(Order::isActive)
                        .collect(Collectors.toList());
            }else{
                return orderRepository.findAll();
            }
        }catch(Exception e){
            log.error("Error accessing the database while fetching all orders.{}",e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Order getOrderById(String orderId) {
        Optional<Order> order;
        try {
            order = orderRepository.findById(orderId).filter(Order::isActive);
        }catch(Exception e){
            log.error("Error accessing database when fetching order with ID: {} {}",orderId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(order.isPresent()) {
            if (!order.get().isActive()) {
                log.info("Requested order with ID: {} is not active", orderId);
            }
            return order.get();
        }else{
            log.error("Requested order with ID: {} not found", orderId);
            throw new CustomException(ErrorMessageConstants.ERROR_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Order> getOrdersOfSupplier(String supplierId) {
        try{
            return orderRepository.findAll().stream()
                    .filter(order -> order.isActive() && order.getSupplierId().equals(supplierId))
                    .collect(Collectors.toList());
        }catch(Exception e){
            log.error("Error accessing database when fetching orders of supplierId:"+supplierId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Order> getOrdersOfConsumer(String consumerId) {
        try{
            return orderRepository.findAll().stream()
                    .filter(order -> order.isActive() && order.getConsumerId().equals(consumerId))
                    .collect(Collectors.toList());
        }catch(Exception e){
            log.error("Error accessing database when fetching orders of supplierId:"+consumerId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MessageObject createOrder(@Valid OrderDTO newOrderDTO) {
        try{
            Order orderToBeSaved = modelMapper.map(newOrderDTO,Order.class);
            log.debug("The Mapped Product is: "+orderToBeSaved.toString());
            orderRepository.save(orderToBeSaved);
            return new MessageObject(MessageConstants.CREATE_SUCCESS);
        }catch(Exception e) {
            log.error("Error accessing database when creating product {} {}",newOrderDTO.toString(),e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MessageObject updateOrder(String orderId) {
        //Only updates the completed status of an order
        Order order = getOrderById(orderId);
        order.setCompleted(true);
        order.setUpdatedDate(LocalDateTime.now());
        try{
            orderRepository.save(order);
            return new MessageObject(MessageConstants.UPDATE_SUCCESS+": Completed :"+orderId);
        }catch(Exception e){
            log.error("Error accessing database when marking order with ID as completed: {} {}",orderId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MessageObject deactivateOrder(String orderId) {
        Order order = getOrderById(orderId);
        order.setActive(false);
        order.setUpdatedDate(LocalDateTime.now());
        try{
            orderRepository.save(order);
            return new MessageObject(MessageConstants.DELETE_SUCCESS+": Deleted :"+orderId);
        }catch(Exception e){
            log.error("Error accessing database when marking order with ID as inactive: {} {}",orderId,e.getMessage());
            throw new CustomException(ErrorMessageConstants.ERROR_DATABASE_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
