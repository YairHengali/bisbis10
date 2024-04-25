package com.att.tdp.bisbis10.dish;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import jakarta.persistence.*;

@Entity
@Table

public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;
    private String name;
    private String description;
    private Integer price;

    public Dish() {
    }

    public Dish(Long id, Restaurant restaurant, String name, String description, Integer price) {
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
