package com.axontest.order.commands;

import com.axontest.order.domains.Subscription;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private Long price;
    private Subscription subscription;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public CreateProductCommand(String productId, Long price, Subscription subscription){
        this.productId = productId;
        this.price = price;
        this.subscription = subscription;
    }

    private CreateProductCommand(){}
}
