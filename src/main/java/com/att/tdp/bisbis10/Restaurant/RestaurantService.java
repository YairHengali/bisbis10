package com.att.tdp.bisbis10.Restaurant;

import com.att.tdp.bisbis10.Exceptions.RestaurantNameAlreadyExistException;
import com.att.tdp.bisbis10.Exceptions.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public void addNewRestaurant(Restaurant restaurantToAdd) {
//        Optional<Restaurant> restaurantByName = restaurantRepository.findRestaurantByName(restaurantToAdd.getName()); //todo: decide if need to validate this
//        if(restaurantByName.isPresent()){
//            throw new RestaurantNameAlreadyExistException(restaurantToAdd.getName());
//        }

        restaurantRepository.save(restaurantToAdd);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getCuisines().contains(cuisine))
                .collect(Collectors.toList());
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    public void updateRestaurant(Long id, Restaurant restaurantUpdates) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

            if (restaurantUpdates.getName() != null) {
                restaurant.setName(restaurantUpdates.getName());
            }
            if (restaurantUpdates.getIsKosher() != null){
                restaurant.setIsKosher(restaurantUpdates.getIsKosher());
            }
            if (restaurantUpdates.getCuisines() != null) {
                restaurant.setCuisines(restaurantUpdates.getCuisines());
            }
    }

    public void deleteRestaurant(Long id) {
        if(!restaurantRepository.existsById(id)){ //todo: needs to validate?? or its ok if not exist
            throw new RestaurantNotFoundException(id);
        }

        restaurantRepository.deleteById(id);
    }
}
