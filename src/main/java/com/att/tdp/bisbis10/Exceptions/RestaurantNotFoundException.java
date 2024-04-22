package com.att.tdp.bisbis10.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestaurantNotFoundException extends ResponseStatusException {
    public RestaurantNotFoundException(Long id){
        super(HttpStatus.NOT_FOUND, "Restaurant with ID " + id +" does not found");
    }
}
