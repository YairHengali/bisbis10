package com.att.tdp.bisbis10.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
//    Optional<Restaurant> findRestaurantByName(String name);
}
