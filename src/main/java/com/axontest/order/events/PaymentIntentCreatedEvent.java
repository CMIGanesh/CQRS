package com.axontest.order.events;


public class PaymentIntentCreatedEvent {

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


    private String paymentIntentId;
    private String status;

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentIntentCreatedEvent(String orderId, String paymentIntentId, String status){
        this.orderId = orderId;
        this.paymentIntentId = paymentIntentId;
        this.status = status;
    }
}
