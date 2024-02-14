package com.summit.order.controller;

import com.summit.order.Service.OrderService;
import com.summit.order.dto.OrderDTO;
import com.summit.order.response.ResponseObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseObject> getOrders(
            @RequestParam(name = "active", defaultValue = "true") boolean onlyActive,
            @RequestParam(name = "supplierId", required = false) String supplierId,
            @RequestParam(name = "consumerId", required = false) String consumerId
    ) {
        ResponseObject responseObject;

        if (supplierId != null) {
            responseObject = new ResponseObject(orderService.getOrdersOfSupplier(supplierId), HttpStatus.OK);
        } else if (consumerId != null) {
            responseObject = new ResponseObject(orderService.getOrdersOfConsumer(consumerId), HttpStatus.OK);
        } else {
            responseObject = new ResponseObject(orderService.getOrders(onlyActive), HttpStatus.OK);
        }

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping(path = "{orderId}")
    public ResponseEntity<ResponseObject> getOrderById(
            @PathVariable String orderId
    ) {
        ResponseObject responseObject = new ResponseObject(orderService.getOrderById(orderId), HttpStatus.OK);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createOrder(
            @Valid @RequestBody OrderDTO orderDTO
    ){
        log.debug("Order Obtained: {}",orderDTO.toString());
        ResponseObject responseObject = new ResponseObject(orderService.createOrder(orderDTO), HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }

    @DeleteMapping(path="{orderId}")
    public ResponseEntity<ResponseObject> deleteOrder(
            @PathVariable String orderId
    ){
        ResponseObject responseObject = new ResponseObject(orderService.deactivateOrder(orderId),HttpStatus.OK);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }


}
