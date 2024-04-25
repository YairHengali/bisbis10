package com.att.tdp.bisbis10.dish;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DishRequestDTO(@NotBlank(message = "name is mandatory") String name,
                             @NotBlank(message = "description is mandatory") String description,
                             @NotNull(message = "price is mandatory")
                             @Positive(message = "price must be positive") Integer price) {
    public DishRequestDTO(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Integer price() {
        return price;
    }
}
