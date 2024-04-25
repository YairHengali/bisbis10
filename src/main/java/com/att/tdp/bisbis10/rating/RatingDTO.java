package com.att.tdp.bisbis10.rating;

import jakarta.validation.constraints.*;

public record RatingDTO(@NotNull(message = "Restaurant ID is mandatory") Long restaurantId,
                        @NotNull(message = "rating is mandatory")
                        @Min(value = 0, message = "rating must be between 0 to 5")
                        @Max(value = 5, message = "rating must be between 0 to 5") Float rating) {
    public RatingDTO(Long restaurantId, Float rating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
    }

    @Override
    public Long restaurantId() {
        return restaurantId;
    }

    @Override
    public Float rating() {
        return rating;
    }

}
