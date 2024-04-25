package com.att.tdp.bisbis10.restaurant;

import java.util.Set;

public class RestaurantResponseDTO {
    private final Long id;
    private final String name;
    private final Float averageRating;
    private final Boolean isKosher;
    private final Set<String> cuisines;

    public RestaurantResponseDTO(Long id, String name, Float averageRating, Boolean isKosher, Set<String> cuisines) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public Boolean getKosher() {
        return isKosher;
    }

    public Set<String> getCuisines() {
        return cuisines;
    }
}
