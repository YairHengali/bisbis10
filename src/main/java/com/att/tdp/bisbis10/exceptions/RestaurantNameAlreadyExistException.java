package com.att.tdp.bisbis10.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestaurantNameAlreadyExistException extends ResponseStatusException {
    public RestaurantNameAlreadyExistException(String name){
        super(HttpStatus.BAD_REQUEST, "Restaurant with the name: \"" + name + "\" is already exists");
    }
}
