package com.axontest.order.services;

import com.axontest.order.domains.Product;
import com.axontest.order.events.ProductCreatedEvent;
import com.axontest.order.query.FindAllProductsQuery;
import com.axontest.order.query.FindProductQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductEventHandler {

    private final Map<String, Product> products = new HashMap<>();

    @EventHandler
    public void on(ProductCreatedEvent event) {
        String productId = event.getProductId();
        products.put(productId, new Product(productId, event.getPrice(), event.getSubscription()));
    }

    @QueryHandler
    public Product handle(FindProductQuery query){
        return products.get(query.getProductId());
    }

    @QueryHandler
    public List<Product> handle(FindAllProductsQuery query) {
        return new ArrayList<>(products.values());
    }
}
