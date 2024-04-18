package com.att.tdp.bisbis10.Restaurant;

import com.att.tdp.bisbis10.Rating.Rating;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

import static java.lang.Float.sum;

@Entity
@Table
public class Restaurant {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isKosher;
    private Set<String> cuisines;

    @Transient
    private Float averageRating;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, Boolean isKosher, Set<String> cuisines) {
        this.id = id;
        this.name = name;
//        this.averageRating = averageRating;
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
        for( Rating rating : ratings){
            ratingsSum += rating.getRating();
        }

        return ratingsSum / ratings.size();
//        return averageRating;
    }

//    public void setAverageRating(Float averageRating) {
//        this.averageRating = averageRating;
//    }

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
