package com.att.tdp.bisbis10.Restaurant;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(nullable = true)
    private Float rating;
    private Boolean isKosher;
    private Set<String> cuisines;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, Float rating, Boolean isKosher, Set<String> cuisines) {
        this.id = id;
        this.name = name;
        this.rating = rating;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", isKosher=" + isKosher +
                ", cuisines=" + cuisines +
                '}';
    }
}
