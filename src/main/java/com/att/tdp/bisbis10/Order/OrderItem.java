package com.att.tdp.bisbis10.Order;

import jakarta.persistence.*;

@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // todo: maybe some parameters here can resolve my problems? (fetch = FetchType.LAZY), maybe this also: @JsonIgnore ?? read about it and understand
    @JoinColumn(name = "order_id") //TODO: when commenting those 2 lines it also worked
    private RestaurantOrder restaurantOrder;
    private Long dishId;
    private Integer amount;

    public OrderItem() {
    }

//    public OrderItem(Long id, Long dishId, Integer amount) {
//        this.id = id;
//        this.dishId = dishId;
//        this.amount = amount;
//    }
    public OrderItem(Long id, RestaurantOrder restaurantOrder, Long dishId, Integer amount) {
        this.id = id;
        this.restaurantOrder = restaurantOrder;
        this.dishId = dishId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RestaurantOrder getOrder() {
        return restaurantOrder;
    }

    public void setOrder(RestaurantOrder restaurantOrder) {
        this.restaurantOrder = restaurantOrder;
    }
}
