package com.summit.order.Service;

import com.summit.order.dto.OrderDTO;
import com.summit.order.model.Order;
import com.summit.order.response.MessageObject;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(boolean onlyActiveOrders);
    Order getOrderById(String orderId);
    List<Order> getOrdersOfSupplier(String supplierId);
    List<Order> getOrdersOfConsumer(String consumerId);
    MessageObject createOrder(@Valid OrderDTO newOrder);
    MessageObject updateOrder(String orderId);
    MessageObject deactivateOrder(String orderId);
}
