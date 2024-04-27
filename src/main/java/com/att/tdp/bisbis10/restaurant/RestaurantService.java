package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.exceptions.EmptyNameException;
import com.att.tdp.bisbis10.exceptions.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

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

//    @Transactional
//    public void updateRestaurant(Long id, Restaurant restaurantUpdates) {
//        Restaurant restaurant = restaurantRepository.findById(id)
//                .orElseThrow(() -> new RestaurantNotFoundException(id));
//
//            if (restaurantUpdates.getName() != null) {
//                restaurant.setName(restaurantUpdates.getName());
//            }
//            if (restaurantUpdates.getIsKosher() != null){
//                restaurant.setIsKosher(restaurantUpdates.getIsKosher());
//            }
//            if (restaurantUpdates.getCuisines() != null) {
//                restaurant.setCuisines(restaurantUpdates.getCuisines());
//            }
//    }
@Transactional
public void updateRestaurant(Long id, RestaurantRequestDTO restaurantUpdates) {
    Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new RestaurantNotFoundException(id));

    if (restaurantUpdates.name() != null) {
        if (restaurantUpdates.name().isEmpty()) {
            throw new EmptyNameException();
        }
        restaurant.setName(restaurantUpdates.name());
    }

    if (restaurantUpdates.isKosher() != null){
        restaurant.setIsKosher(restaurantUpdates.isKosher());
    }
    if (restaurantUpdates.cuisines() != null) {
        if (restaurantUpdates.cuisines().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cuisines must contain at least one cuisine");
        }
        restaurant.setCuisines(restaurantUpdates.cuisines());
    }
}

    public void deleteRestaurant(Long id) {
        if(!restaurantRepository.existsById(id)){
            throw new RestaurantNotFoundException(id);
        }

        restaurantRepository.deleteById(id);
    }
}
