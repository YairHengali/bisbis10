package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.exceptions.RestaurantNotFoundException;
import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void addRating(RatingDTO ratingDTO) {
        Restaurant restaurant = restaurantRepository.findById(ratingDTO.restaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ratingDTO.restaurantId()));

        ratingRepository.save(new Rating(restaurant, ratingDTO.rating()));
    }
}
