package com.att.tdp.bisbis10.order;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class RestaurantOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "restaurantId is mandatory")
    private Long restaurantId;

    @Valid
    @NotEmpty(message = "An order must contain at least one item")
    @ElementCollection
    private List<OrderItem> orderItems;

    public RestaurantOrder() {
    }

    public RestaurantOrder(UUID id, Long restaurantId, List<OrderItem> orderItems) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}