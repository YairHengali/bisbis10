package com.att.tdp.bisbis10.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Embeddable
public class OrderItem {
    @NotNull(message = "dish ID is mandatory")
    private Long dishId;
    @NotNull(message = "amount is mandatory")
    @Positive(message = "amount must be positive")
    private Integer amount;

    public OrderItem(){

    }

    public OrderItem(Long dishId, Integer amount) {
        this.dishId = dishId;
        this.amount = amount;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
