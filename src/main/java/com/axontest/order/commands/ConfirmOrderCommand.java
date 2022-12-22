package com.axontest.order.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ConfirmOrderCommand {

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @TargetAggregateIdentifier
    private String orderId;

    // constructor, getters, equals/hashCode and toString
    public ConfirmOrderCommand(String orderId){
        this.orderId = orderId;
    }

    protected ConfirmOrderCommand(){}
}
