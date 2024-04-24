package com.att.tdp.bisbis10.Restaurant;

import com.att.tdp.bisbis10.Exceptions.RestaurantNotFoundException;
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

    public List<RestaurantDTO> getRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertRestaurantToDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantDTO> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getCuisines().contains(cuisine))
                .map(this::convertRestaurantToDTO)
                .collect(Collectors.toList());
    }

    private RestaurantDTO convertRestaurantToDTO(Restaurant restaurant){
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAverageRating(),
                restaurant.getIsKosher(),
                restaurant.getCuisines()
        );
    }

    public void addRestaurant(Restaurant restaurantToAdd) {
//        Optional<Restaurant> restaurantByName = restaurantRepository.findRestaurantByName(restaurantToAdd.getName()); //todo: decide if need to validate this
//        if(restaurantByName.isPresent()){
//            throw new RestaurantNameAlreadyExistException(restaurantToAdd.getName());
//        }

        restaurantRepository.save(restaurantToAdd);
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
