package com.attech.estore.exception;

public class StaffNotAuthorizedException extends RuntimeException{
    String message = "";

    public StaffNotAuthorizedException (String message) {
        super(message);
    }
}
