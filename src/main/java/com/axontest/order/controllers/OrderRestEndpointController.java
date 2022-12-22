package com.axontest.order.controllers;

import com.axontest.order.commands.CreateOrderCommand;
import com.axontest.order.commands.CreatePaymentIntentCommand;
import com.axontest.order.domains.Order;
import com.axontest.order.domains.Product;
import com.axontest.order.query.FindAllOrderedProductsQuery;
import com.axontest.order.query.FindOrderedProductQuery;
import com.axontest.order.query.FindProductQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cart")
public class OrderRestEndpointController {


    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public OrderRestEndpointController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    @PostMapping("/createOrder")
    public CompletableFuture<Void> shipOrder(@RequestBody Map<String, String> body) {
        String orderId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateOrderCommand(orderId, body.get("productId")));

    }

    @PostMapping("/checkout/{orderId}")
    public CompletableFuture<Void> checkout(@PathVariable("orderId") String orderId)
    {
        CompletableFuture<Order> order = queryGateway.query(new FindOrderedProductQuery(orderId), ResponseTypes.instanceOf(Order.class));
        String productId =  order.join().getProductId();
        CompletableFuture<Product> product = queryGateway.query(new FindProductQuery(productId), ResponseTypes.instanceOf(Product.class));
        long price = product.join().getPrice();
        return commandGateway.send(new CreatePaymentIntentCommand(orderId, price));

    }

    @GetMapping("/order/{orderId}")
    public Order findOrder(@PathVariable("orderId") String orderId) {
        CompletableFuture<Order> order = queryGateway.query(new FindOrderedProductQuery(orderId), ResponseTypes.instanceOf(Order.class));
        return order.join();
    }

    @GetMapping("/all-orders")
    public List<Order> findAllOrders() {
        CompletableFuture<List<Order>> ordersList = queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(Order.class));
        return ordersList.join();
    }



}
