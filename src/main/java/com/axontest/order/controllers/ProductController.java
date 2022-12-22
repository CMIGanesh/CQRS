package com.axontest.order.controllers;

import com.axontest.order.commands.CreateProductCommand;
import com.axontest.order.domains.Product;
import com.axontest.order.domains.Subscription;
import com.axontest.order.query.FindAllProductsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
public class ProductController {

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public ProductController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/AddProduct")
    public CompletableFuture<Void> addProduct(@RequestBody Map<String, String> body) {
        String productId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateProductCommand(productId, Long.parseLong(body.get("price")), Subscription.valueOf(body.get("subscription"))));
    }

    @GetMapping("/allProducts")
    @CrossOrigin
    public List<Product> findAllProducts() {

        CompletableFuture<List<Product>> productsList = queryGateway.query(new FindAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class));
        return productsList.join();
    }


}
