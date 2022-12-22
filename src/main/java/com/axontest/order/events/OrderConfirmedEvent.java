package com.axontest.order.events;

public class OrderConfirmedEvent {

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;

    protected OrderConfirmedEvent(){}

    public OrderConfirmedEvent(String orderId){
        this.orderId = orderId;
    }

    // default constructor, getters, equals/hashCode and toString
}
