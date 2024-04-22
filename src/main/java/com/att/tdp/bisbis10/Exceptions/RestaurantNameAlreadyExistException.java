package com.att.tdp.bisbis10.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestaurantNameAlreadyExistException extends ResponseStatusException { //todo: decide if keep it or its ok to have 2 with same name
    public RestaurantNameAlreadyExistException(String name){
        super(HttpStatus.BAD_REQUEST, "Restaurant with the name: \"" + name + "\" is already exists");
    }
}
