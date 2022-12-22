package com.axontest.order.domains;

public class Product {

    private String productId;
    private long price;
    private Subscription subscription;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Product(String productId, long price, Subscription subscription){
        this.productId = productId;
        this.price = price;
        this.subscription = subscription;
    }
}
