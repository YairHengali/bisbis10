package com.att.tdp.bisbis10.restaurant;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantResponseDTO> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @GetMapping(params = "cuisine")
    public List<RestaurantResponseDTO> getRestaurantsByCuisine(@RequestParam("cuisine") String cuisine) {
        return restaurantService.getRestaurantsByCuisine(cuisine);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> addRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurantToAdd){
        restaurantService.addRestaurant(restaurantToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateRestaurant(@PathVariable("id") Long id, @RequestBody Restaurant restaurantUpdates) {
        restaurantService.updateRestaurant(id, restaurantUpdates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
