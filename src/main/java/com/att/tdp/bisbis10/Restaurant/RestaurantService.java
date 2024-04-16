package com.att.tdp.bisbis10.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
        Optional<Restaurant> restaurantByName = restaurantRepository.findRestaurantByName(restaurantToAdd.getName());
        if(restaurantByName.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant with this name is already exist");
        }

        restaurantRepository.save(restaurantToAdd);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getCuisines().contains(cuisine))
                .collect(Collectors.toList());
    }

    public Restaurant getRestaurantById(Long id) {
        Optional<Restaurant> restaurantById = restaurantRepository.findRestaurantById(id);
        return restaurantById.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist"));
    }

    public void updateRestaurant(Long id, Restaurant restaurantUpdates) {
        Optional<Restaurant> restaurantById = restaurantRepository.findRestaurantById(id);
        if (restaurantById.isPresent()){
            Restaurant restaurant = restaurantById.get();

            if (restaurantUpdates.getName() != null) {
                restaurant.setName(restaurantUpdates.getName());
            }
            if (restaurantUpdates.getAverageRating() != null) {
                restaurant.setAverageRating(restaurantUpdates.getAverageRating());
            }
            if (restaurantUpdates.getIsKosher() != null){
                restaurant.setIsKosher(restaurantUpdates.getIsKosher());
            }
            if (restaurantUpdates.getCuisines() != null) {
                restaurant.setCuisines(restaurantUpdates.getCuisines());
            }

            restaurantRepository.save(restaurant);


        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist");
        }

    }
}
