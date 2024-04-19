package com.att.tdp.bisbis10.Dish;

import com.att.tdp.bisbis10.Restaurant.Restaurant;
import com.att.tdp.bisbis10.Restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void addDish(Long restId, Dish dishToAdd) {
        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist"));

        dishToAdd.setRestaurant(restaurant);
        dishRepository.save(dishToAdd);
    }

    public Set<Dish> getDishesFromRest(Long restId) {
        restaurantRepository.findById(restId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist"));

        return dishRepository.findAllByRestaurantId(restId);
    }

    @Transactional
    public void updateDishInRest(Long restId, Long dishId, Dish dishUpdates) {
        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist"));

        Dish dish = dishRepository.findDishByRestaurantIdAndId(restId,dishId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish with this ID does not exist in that restaurant"));

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
        dishRepository.deleteById(dishId); //TODO: need to update, because need to go to rest by ID and only there the IDs of dish are uniques
    }
}
