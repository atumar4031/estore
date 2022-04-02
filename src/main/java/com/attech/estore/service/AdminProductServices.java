package com.attech.estore.service;


import com.attech.estore.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminProductServices {
    void addProducts(HttpServletRequest req, HttpServletResponse res);
    boolean updateProducts(HttpServletRequest req, HttpServletResponse res);
    boolean removeProducts(HttpServletRequest req, HttpServletResponse res);
    void approvePurchase();
}
