package com.attech.estore.exception;

public class StaffHasBeenFiredException extends RuntimeException{
    String message = "";
    public StaffHasBeenFiredException (String message) {
        super(message);
    }
}
