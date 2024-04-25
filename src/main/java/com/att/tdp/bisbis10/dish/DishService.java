package com.att.tdp.bisbis10.dish;

import com.att.tdp.bisbis10.exceptions.DishNotFoundException;
import com.att.tdp.bisbis10.exceptions.RestaurantNotFoundException;
import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void addDish(Long restId, Dish dishToAdd) {
        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new RestaurantNotFoundException(restId));

        dishToAdd.setRestaurant(restaurant);
        dishRepository.save(dishToAdd);
    }

    public Set<Dish> getDishesFromRest(Long restId) {
        restaurantRepository.findById(restId)
                .orElseThrow(() -> new RestaurantNotFoundException(restId));

        return dishRepository.findAllByRestaurantId(restId);
    }

    @Transactional
    public void updateDishInRest(Long restId, Long dishId, Dish dishUpdates) {
        restaurantRepository.findById(restId)
                .orElseThrow(() -> new RestaurantNotFoundException(restId));

        Dish dish = dishRepository.findDishByRestaurantIdAndId(restId,dishId)
                .orElseThrow(() -> new DishNotFoundException(restId,dishId));

        if (dishUpdates.getName() != null) {
            dish.setName(dishUpdates.getName());
        }
        if (dishUpdates.getDescription() != null){
            dish.setDescription(dishUpdates.getDescription());
        }
        if (dishUpdates.getPrice() != null) {
            dish.setPrice(dishUpdates.getPrice());
        }
    }

    public void deleteDishInRest(Long restId, Long dishId) {
        dishRepository.deleteById(dishId);
    }
}
