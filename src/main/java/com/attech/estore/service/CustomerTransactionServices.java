package com.attech.estore.service;


public interface CustomerTransactionServices {
    void addProductsToCart();
    void removeProductsFromCart();
    void checkOut();
    void cancelCheckOut();
}
