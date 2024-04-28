package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.dish.Dish;
import com.att.tdp.bisbis10.rating.Rating;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Transient
    private Float averageRating;
    private Boolean isKosher;
    @ElementCollection
    private Set<String> cuisines;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public Restaurant() {
    }

    public Restaurant(String name, Boolean isKosher, Set<String> cuisines) {
        this.name = name;
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
        if (ratings.isEmpty()){
            return null;
        }

        float ratingsSum = 0;
        for (Rating rating : ratings){
            ratingsSum += rating.getRating();
        }

        return ratingsSum / ratings.size();
    }

    public Boolean getIsKosher() {
        return isKosher;
    }

    public void setIsKosher(Boolean isKosher) {
        this.isKosher = isKosher;
    }

    public Set<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
