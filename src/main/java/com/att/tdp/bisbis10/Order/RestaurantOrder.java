package com.att.tdp.bisbis10.Order;

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

//    @OneToMany(mappedBy = "restaurantOrder", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "restaurantOrder", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems; //TODO: ORGENIZE ALL THE TABLES BY NEEDS.. doesnt makes sense that it will look like that

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


/*todo:
   1. in orderItem, the order id is null in the tables (transparent to user - but wierd it doesnt work).
   2. need to check about the tables and their organization - can i make dish ids uniques only for a restaurant?(including their names).
   3. organize exceptions and validation of different methods.
   4. check if they updated the readme - think about how to present info about restaurants in get all.
* */