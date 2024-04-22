package com.att.tdp.bisbis10.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    } //todo: maybe use dto here because not all data needed to be shown

    @GetMapping(params = "cuisine")
    public List<Restaurant> getRestaurantsByCuisine(@RequestParam("cuisine") String cuisine) {
        return restaurantService.getRestaurantsByCuisine(cuisine);
    }

    @GetMapping("/{id}")     //TODO: return all the details from id
    public Restaurant getRestaurantById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> addNewRestaurant(@RequestBody Restaurant restaurantToAdd){
        restaurantService.addNewRestaurant(restaurantToAdd);
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
