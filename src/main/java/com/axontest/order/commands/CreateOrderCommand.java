package com.axontest.order.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;

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

    private String productId;

    // constructor, getters, equals/hashCode and toString
    public CreateOrderCommand(String orderId, String productId){
        this.orderId = orderId;
        this.productId = productId;
    }

    protected CreateOrderCommand(){}
}

