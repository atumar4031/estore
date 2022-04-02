package com.attech.estore.exception;

public class ApplicationDeclinedException extends  RuntimeException{
    String message = "";

    public ApplicationDeclinedException(String message){
        super(message);
    }
}
