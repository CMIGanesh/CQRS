package com.axontest.order.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreatePaymentIntentCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private long price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this. orderId = orderId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CreatePaymentIntentCommand(String orderId, long price){
        this.orderId = orderId;
        this.price = price;
    }

    private CreatePaymentIntentCommand(){}
}
