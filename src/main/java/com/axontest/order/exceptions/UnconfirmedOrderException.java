package com.axontest.order.exceptions;

public class UnconfirmedOrderException extends Exception{
    public UnconfirmedOrderException(String message){
        super(message);
    }
}
