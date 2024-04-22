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


    public void addRating(Rating ratingToAdd, Long restaurantId) {

    }

    public void addRating(RatingRequest ratingRequest) {
        Restaurant restaurant = restaurantRepository.findById(ratingRequest.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ratingRequest.getRestaurantId()));

        ratingRepository.save(new Rating(restaurant, ratingRequest.getRating()));
    }
}
