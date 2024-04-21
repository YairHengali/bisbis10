package com.att.tdp.bisbis10.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class RestaurantOrderController {

    private final RestaurantOrderService restaurantOrderService;

    @Autowired
    public RestaurantOrderController(RestaurantOrderService restaurantOrderService) {
        this.restaurantOrderService = restaurantOrderService;
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> addOrder(@RequestBody RestaurantOrder restaurantOrder) {
        restaurantOrderService.addOrder(restaurantOrder);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("orderId", restaurantOrder.getId().toString()));
    }
}
