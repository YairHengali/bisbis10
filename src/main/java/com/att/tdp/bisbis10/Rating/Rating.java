package com.att.tdp.bisbis10.Rating;

import com.att.tdp.bisbis10.Restaurant.Restaurant;
import jakarta.persistence.*;

@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;
    private float rating;

    public Rating() {
    }

    public Rating(Long id, Restaurant restaurant, float rating) {
        this.id = id;
        this.restaurant = restaurant;
        this.rating = rating;
    }

    public Rating(Restaurant restaurant, float rating) {
        this.restaurant = restaurant;
        this.rating = rating;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
