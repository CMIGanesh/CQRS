package com.axontest.order.services;

import com.axontest.order.domains.Order;
import com.axontest.order.events.OrderConfirmedEvent;
import com.axontest.order.events.OrderCreatedEvent;
import com.axontest.order.events.OrderShippedEvent;
import com.axontest.order.events.PaymentIntentCreatedEvent;
import com.axontest.order.query.FindAllOrderedProductsQuery;
import com.axontest.order.query.FindOrderedProductQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersEventHandler {

    private final Map<String, Order> orders = new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent event) {
        String orderId = event.getOrderId();
        orders.put(orderId, new Order(orderId, event.getProductId()));
    }

    @EventHandler
    public void on(OrderConfirmedEvent event){
        String orderId = event.getOrderId();
        Order order = orders.get(orderId);
        order.setOrderConfirmed();
    }

    @EventHandler
    public void on(OrderShippedEvent event){
        String orderId = event.getOrderId();
        Order order = orders.get(orderId);
        order.setOrderCompleted();
    }

    @EventHandler
    public void on(PaymentIntentCreatedEvent event){
        String orderId = event.getOrderId();
        Order order = orders.get(orderId);
        order.setPaymentIntentId(event.getPaymentIntentId());
    }

    @QueryHandler
    public Order handle(FindOrderedProductQuery query){
        return orders.get(query.getOrderId());
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orders.values());
    }
}
