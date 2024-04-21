package com.att.tdp.bisbis10.Order;

import com.att.tdp.bisbis10.Dish.DishRepository;
import com.att.tdp.bisbis10.Restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RestaurantOrderService {

    private final RestaurantOrderRepository restaurantOrderRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Autowired
    public RestaurantOrderService(RestaurantOrderRepository restaurantOrderRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantOrderRepository = restaurantOrderRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    public void addOrder(RestaurantOrder restaurantOrder) {
        restaurantRepository.findById(restaurantOrder.getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist"));

        for (OrderItem orderItem : restaurantOrder.getOrderItems()){
            dishRepository.findDishByRestaurantIdAndId(restaurantOrder.getRestaurantId(),orderItem.getDishId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish with ID: " + orderItem.getDishId() + " does not exist in that restaurant"));
        }

        restaurantOrderRepository.save(restaurantOrder);
    }
}
