package com.att.tdp.bisbis10.Dish;

import com.att.tdp.bisbis10.Restaurant.Restaurant;
import jakarta.persistence.*;

@Entity
@Table
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurantId", "name"})})

public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;
    private String name;
    private String description;
    private Float price;

    public Dish() {
    }

    public Dish(Long id, Restaurant restaurant, String name, String description, Float price) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
