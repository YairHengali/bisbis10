package com.att.tdp.bisbis10.Rating;

public class RatingRequest {
    private Long restaurantId;
    private float rating;

    public RatingRequest(Long restaurantId, float rating) {
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
