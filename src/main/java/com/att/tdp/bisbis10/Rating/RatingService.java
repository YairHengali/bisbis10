package com.att.tdp.bisbis10.Rating;

import com.att.tdp.bisbis10.Exceptions.RestaurantNotFoundException;
import com.att.tdp.bisbis10.Restaurant.Restaurant;
import com.att.tdp.bisbis10.Restaurant.RestaurantRepository;
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
        Restaurant restaurant = restaurantRepository.findById(ratingDTO.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ratingDTO.getRestaurantId()));

        ratingRepository.save(new Rating(restaurant, ratingDTO.getRating()));
    }
}
