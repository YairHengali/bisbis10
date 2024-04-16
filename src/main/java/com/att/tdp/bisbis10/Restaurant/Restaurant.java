package com.att.tdp.bisbis10.Restaurant;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(nullable = true)
    private Float averageRating;
    private Boolean isKosher;
    private Set<String> cuisines;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, Float averageRating, Boolean isKosher, Set<String> cuisines) {
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
                ", averageRating=" + averageRating +
                ", isKosher=" + isKosher +
                ", cuisines=" + cuisines +
                '}';
    }
}
