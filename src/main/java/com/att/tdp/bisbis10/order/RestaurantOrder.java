package com.att.tdp.bisbis10.order;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class RestaurantOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Long restaurantId;
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