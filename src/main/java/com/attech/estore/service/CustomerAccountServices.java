package com.attech.estore.service;

import com.attech.estore.dto.CustomerDto;
import com.attech.estore.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CustomerAccountServices {
    CustomerDto signUp(HttpServletRequest req, HttpServletResponse res);
    CustomerDto logIn(HttpServletRequest req, HttpServletResponse res);
    void viewProfile();
    void updateProfile();
    void logOut();
}
