package com.att.tdp.bisbis10.Order;

import com.att.tdp.bisbis10.Dish.DishRepository;
import com.att.tdp.bisbis10.Exceptions.DishNotFoundException;
import com.att.tdp.bisbis10.Exceptions.RestaurantNotFoundException;
import com.att.tdp.bisbis10.Restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantOrder.getRestaurantId()));

        for (OrderItem orderItem : restaurantOrder.getOrderItems()){
            dishRepository.findDishByRestaurantIdAndId(restaurantOrder.getRestaurantId(),orderItem.getDishId())
                    .orElseThrow(() -> new DishNotFoundException(restaurantOrder.getRestaurantId(), orderItem.getDishId()));
        }

        restaurantOrderRepository.save(restaurantOrder);
    }
}
