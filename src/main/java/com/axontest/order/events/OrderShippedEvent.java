package com.axontest.order.events;

public class OrderShippedEvent {

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;

    public OrderShippedEvent(String orderId){
        this.orderId = orderId;
    }

    protected OrderShippedEvent(){}

    // default constructor, getters, equals/hashCode and toString
}
