package com.att.tdp.bisbis10.rating;

public class RatingDTO {
    private Long restaurantId;
    private float rating;

    public RatingDTO(Long restaurantId, float rating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
