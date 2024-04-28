package com.att.tdp.bisbis10.dish;

import com.att.tdp.bisbis10.exceptions.DishNotFoundException;
import com.att.tdp.bisbis10.exceptions.EmptyNameException;
import com.att.tdp.bisbis10.exceptions.RestaurantNotFoundException;
import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public void addDish(Long restId, DishRequestDTO dishRequestDTO) {
        Restaurant restaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new RestaurantNotFoundException(restId));

        dishRepository.save(new Dish(restaurant, dishRequestDTO.name(), dishRequestDTO.description(), dishRequestDTO.price()));
    }

    public List<Dish> getDishesFromRest(Long restId) {
        restaurantRepository.findById(restId)
                .orElseThrow(() -> new RestaurantNotFoundException(restId));

        return dishRepository.findAllByRestaurantId(restId);
    }

    @Transactional
    public void updateDishInRest(Long restId, Long dishId, DishRequestDTO dishUpdates) {
        restaurantRepository.findById(restId)
                .orElseThrow(() -> new RestaurantNotFoundException(restId));

        Dish dish = dishRepository.findDishByRestaurantIdAndId(restId,dishId)
                .orElseThrow(() -> new DishNotFoundException(restId,dishId));

        if (dishUpdates.name() != null) {
            if(dishUpdates.name().isEmpty()){
                throw new EmptyNameException();
            }
            dish.setName(dishUpdates.name());
        }
        if (dishUpdates.description() != null){
            dish.setDescription(dishUpdates.description());
        }
        if (dishUpdates.price() != null) {
            if(dishUpdates.price() <= 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "price must be positive");
            }
            dish.setPrice(dishUpdates.price());
        }
    }

    public void deleteDishInRest(Long restId, Long dishId) {
        if(!restaurantRepository.existsById(restId)){
            throw new RestaurantNotFoundException(restId);
        }
        if(!dishRepository.existsById(dishId)){
            throw new DishNotFoundException(restId, dishId);
        }
        dishRepository.deleteById(dishId);
    }
}
