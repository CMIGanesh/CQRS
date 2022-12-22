package com.axontest.order.domains;

import org.axonframework.modelling.command.EntityId;


public class PaymentIntent {
    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @EntityId
    private String paymentIntentId;
    private String status;

    public PaymentIntent(String paymentIntentId, String status){
        this.paymentIntentId = paymentIntentId;
        this.status = status;
    }
}
