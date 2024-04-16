package com.att.tdp.bisbis10.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
//@RequestMapping(path = "restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @GetMapping(params = "cuisine")
    public List<Restaurant> getRestaurantsByCuisine(@RequestParam("cuisine") String cuisine) {
        return restaurantService.getRestaurantsByCuisine(cuisine);
    }

    @GetMapping("/{id}")     //TODO: return all the details from id

    public Restaurant getRestaurantById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping()
    public void addNewRestaurant(@RequestBody Restaurant restaurantToAdd){
        restaurantService.addNewRestaurant(restaurantToAdd);
    }

    @PutMapping("/{id}")
    public void updateRestaurant(@PathVariable("id") Long id, @RequestBody Restaurant restaurantUpdates) {
        restaurantService.updateRestaurant(id, restaurantUpdates);
    }
        /*
    TODO: ADD DELETE ENDPOINT
     */
}
