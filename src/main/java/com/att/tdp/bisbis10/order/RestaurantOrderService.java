package com.att.tdp.bisbis10.order;
import com.att.tdp.bisbis10.dish.DishRepository;
import com.att.tdp.bisbis10.exceptions.DishNotFoundException;
import com.att.tdp.bisbis10.exceptions.RestaurantNotFoundException;
import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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
