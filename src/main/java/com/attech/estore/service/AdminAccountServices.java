package com.attech.estore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminAccountServices {
    void signUp();
    void logIn ();
    void viewProfile();
    void updateProfile();
    void logOut();}
