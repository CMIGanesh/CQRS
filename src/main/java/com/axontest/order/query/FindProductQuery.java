package com.axontest.order.query;

public class FindProductQuery {
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public FindProductQuery(String productId){
        this.productId = productId;
    }

    private FindProductQuery(){}
}
