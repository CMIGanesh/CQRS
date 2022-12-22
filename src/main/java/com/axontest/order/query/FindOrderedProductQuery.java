package com.axontest.order.query;

public class FindOrderedProductQuery {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public FindOrderedProductQuery(String orderId){
        this.orderId = orderId;
    }
}
