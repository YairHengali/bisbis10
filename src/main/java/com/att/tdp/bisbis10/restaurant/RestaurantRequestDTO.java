package com.att.tdp.bisbis10.restaurant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RestaurantRequestDTO(@NotBlank(message = "name is mandatory") String name,
                                   @NotNull(message = "isKosher is mandatory") Boolean isKosher,
                                   @NotEmpty(message = "must have at least one cuisine") Set<String> cuisines) {

    public RestaurantRequestDTO(String name, Boolean isKosher, Set<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Boolean isKosher() {
        return isKosher;
    }

    @Override
    public Set<String> cuisines() {
        return cuisines;
    }
}
