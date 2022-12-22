package com.axontest.order.events;

public class OrderCreatedEvent {

    private String orderId;
    private String productId;
    public OrderCreatedEvent(String orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    // default constructor, getters, equals/hashCode and toString
}
