package com.attech.estore.exception;

public class ApplicantAlreadyExistsException extends RuntimeException{

    String message = "";

    public ApplicantAlreadyExistsException(String message){
        super(message);
    }
}
