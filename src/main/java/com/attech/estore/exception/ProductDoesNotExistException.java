package com.attech.estore.exception;

public class ProductDoesNotExistException extends RuntimeException{
    String message = "";

    public ProductDoesNotExistException(String message){
        super(message);
    }

}
