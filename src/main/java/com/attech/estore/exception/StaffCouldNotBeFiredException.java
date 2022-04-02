package com.attech.estore.exception;

public class StaffCouldNotBeFiredException extends RuntimeException{
    String message = "";

    public StaffCouldNotBeFiredException (String message){
        super(message);
    }
}
