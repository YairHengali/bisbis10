package com.att.tdp.bisbis10.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByRestaurantId(long restId);

    Optional<Dish> findDishByRestaurantIdAndId(long restId, long dishId);
}
