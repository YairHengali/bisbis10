package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.exceptions.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantResponseDTO> getRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertRestaurantToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantResponseDTO> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getCuisines().contains(cuisine))
                .map(this::convertRestaurantToResponseDTO)
                .collect(Collectors.toList());
    }

    private RestaurantResponseDTO convertRestaurantToResponseDTO(Restaurant restaurant){
        return new RestaurantResponseDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAverageRating(),
                restaurant.getIsKosher(),
                restaurant.getCuisines()
        );
    }

    public void addRestaurant(RestaurantRequestDTO restaurantToAdd) {
//        Optional<Restaurant> restaurantByName = restaurantRepository.findRestaurantByName(restaurantToAdd.getName());
//        if(restaurantByName.isPresent()){
//            throw new RestaurantNameAlreadyExistException(restaurantToAdd.getName());
//        }

        restaurantRepository.save(new Restaurant(restaurantToAdd.name(), restaurantToAdd.isKosher(), restaurantToAdd.cuisines()));
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
        if(!restaurantRepository.existsById(id)){
            throw new RestaurantNotFoundException(id);
        }

        restaurantRepository.deleteById(id);
    }
}
