package com.attech.estore.exception;

public class QuantityIsGreaterException extends RuntimeException{
    String message = "";

    public QuantityIsGreaterException (String message) {
        super(message);
    }
}
