package com.att.tdp.bisbis10.dish;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restId}/dishes")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<Void> addDishToRest(@PathVariable("restId") Long restId, @RequestBody @Valid DishRequestDTO dishRequestDTO) {
        dishService.addDish(restId, dishRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Dish> getDishesFromRest(@PathVariable("restId") Long restId){
           return dishService.getDishesFromRest(restId);
    }

    @PutMapping("/{dishId}")
    public void updateDishInRest(@PathVariable("restId") Long restId, @PathVariable("dishId") Long dishId, @RequestBody DishRequestDTO dishUpdates){
        dishService.updateDishInRest(restId, dishId, dishUpdates);
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDishInRest(@PathVariable("restId") Long restId, @PathVariable("dishId") Long dishId) {
        dishService.deleteDishInRest(restId, dishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}