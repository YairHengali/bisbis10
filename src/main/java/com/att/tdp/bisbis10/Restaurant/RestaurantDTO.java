package com.att.tdp.bisbis10.Restaurant;

import com.att.tdp.bisbis10.Dish.Dish;
import com.att.tdp.bisbis10.Rating.Rating;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class RestaurantDTO {
    private Long id;
    private String name;
    private Float averageRating;
    private Boolean isKosher;
    private Set<String> cuisines;

    public RestaurantDTO(Long id, String name, Float averageRating, Boolean isKosher, Set<String> cuisines) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public Boolean getIsKosher() {
        return isKosher;
    }

    public void setIsKosher(Boolean isKosher) {
        isKosher = isKosher;
    }

    public Set<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<String> cuisines) {
        this.cuisines = cuisines;
    }
}
