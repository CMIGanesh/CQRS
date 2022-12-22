package com.axontest.order.domains;

import com.axontest.order.commands.ConfirmOrderCommand;
import com.axontest.order.commands.CreateOrderCommand;
import com.axontest.order.commands.CreatePaymentIntentCommand;
import com.axontest.order.commands.ShipOrderCommand;
import com.axontest.order.events.OrderConfirmedEvent;
import com.axontest.order.events.OrderCreatedEvent;
import com.axontest.order.events.OrderShippedEvent;
import com.axontest.order.events.PaymentIntentCreatedEvent;
import com.axontest.order.exceptions.UnconfirmedOrderException;
import com.stripe.Stripe;
import com.stripe.param.PaymentIntentCreateParams;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import com.axontest.order.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @AggregateMember
    private PaymentIntent paymentIntent;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        apply(new OrderCreatedEvent(command.getOrderId(), command.getProductId()));
    }

    @EventSourcingHandler
    public void on (OrderCreatedEvent event){
        this.orderId = event.getOrderId();
        this.orderConfirmed = false;
    }

    public OrderAggregate(){}

    @CommandHandler
    public void handle(ConfirmOrderCommand command){
        if(orderConfirmed){
            return;
        }

        apply(new OrderConfirmedEvent(orderId));
    }

    @EventSourcingHandler
    public void on (OrderConfirmedEvent event){
        orderConfirmed = true;
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) throws UnconfirmedOrderException {
        if(!orderConfirmed){
            throw new UnconfirmedOrderException("order not confirmed");
        }
        apply(new OrderShippedEvent(orderId));
    }

    @CommandHandler
    public void handle(CreatePaymentIntentCommand command){
        if(paymentIntent == null) {
            Map<String, String> retVal = createPaymentIntent(command.getPrice());
            if (retVal.get(Constants.SECRET_KEY) != null) {
                apply(new PaymentIntentCreatedEvent(command.getOrderId(), retVal.get(Constants.SECRET_KEY), retVal.get("status")));
            }
        }
        return;
    }

    @EventSourcingHandler
    public void on(PaymentIntentCreatedEvent event){
        this.paymentIntent = new PaymentIntent(event.getPaymentIntentId(), event.getStatus());
    }

    private Map<String, String> createPaymentIntent(Long price){
        Map<String, String> retVal = new HashMap<>();
        setConfig();
        try {
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams
                            .builder()
                            .setAmount(price * 100)
                            .setCurrency("inr")
                            .addPaymentMethodType("card")
                            .build();

            com.stripe.model.PaymentIntent intent = com.stripe.model.PaymentIntent.create(params);
            retVal.put(Constants.SECRET_KEY, intent.getClientSecret());
            retVal.put("status", intent.getStatus());
            return retVal;
        }catch (Exception e){
            retVal.put("success", "false");
            retVal.put("error", e.getMessage());
        }
        return retVal;
    }

    private static void setConfig(){
        Stripe.apiKey = Constants.API_KEY;
    }
}
