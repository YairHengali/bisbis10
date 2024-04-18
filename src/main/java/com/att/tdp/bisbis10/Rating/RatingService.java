package com.att.tdp.bisbis10.Rating;

import com.att.tdp.bisbis10.Restaurant.Restaurant;
import com.att.tdp.bisbis10.Restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with this ID does not exist"));

        ratingRepository.save(new Rating(restaurant, ratingRequest.getRating()));
    }
}
