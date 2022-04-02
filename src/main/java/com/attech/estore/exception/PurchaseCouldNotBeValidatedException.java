package com.attech.estore.exception;

public class PurchaseCouldNotBeValidatedException extends RuntimeException{
    String message = "";

    public PurchaseCouldNotBeValidatedException (String message) {
        super(message);
    }
}
