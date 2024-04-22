package com.att.tdp.bisbis10.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DishNotFoundException extends ResponseStatusException {
    public DishNotFoundException(long restaurantId, long dishId) {
        super(HttpStatus.NOT_FOUND, "Dish with ID: " + dishId + " does not found in restaurant with ID: " + restaurantId);
    }
}
