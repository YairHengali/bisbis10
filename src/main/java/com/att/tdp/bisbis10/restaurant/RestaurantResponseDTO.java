package com.att.tdp.bisbis10.restaurant;

import java.util.Set;

public record RestaurantResponseDTO(Long id, String name, Float averageRating, Boolean isKosher, Set<String> cuisines) {
}
