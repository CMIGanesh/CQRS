package com.axontest.order.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // constructor, getters, equals/hashCode and toString
    public ShipOrderCommand(String orderId){
        this.orderId = orderId;
    }

    protected ShipOrderCommand(){}
}
