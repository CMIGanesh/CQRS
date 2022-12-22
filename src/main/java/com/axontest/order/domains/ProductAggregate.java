package com.axontest.order.domains;

import com.axontest.order.commands.CreateProductCommand;
import com.axontest.order.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        apply(new ProductCreatedEvent(command.getProductId(), command.getPrice(), command.getSubscription()));
    }

    @EventSourcingHandler
    public void on (ProductCreatedEvent event){
        this.productId = event.getProductId();
    }

    public ProductAggregate(){}
}
