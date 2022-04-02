package com.attech.estore.exception;

public class StaffExistsException extends RuntimeException {
    String message = "";

    public StaffExistsException (String message) {
        super(message);
    }


}
