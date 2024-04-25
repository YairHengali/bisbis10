package com.att.tdp.bisbis10.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    public Set<Dish> findAllByRestaurantId(long restId);

    public Optional<Dish> findDishByRestaurantIdAndId(long restId, long dishId);
}