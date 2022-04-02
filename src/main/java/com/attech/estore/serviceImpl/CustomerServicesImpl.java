package com.attech.estore.serviceImpl;


import com.attech.estore.dao.CustomerDao;
import com.attech.estore.dto.CustomerDto;

import com.attech.estore.service.CustomerAccountServices;
import com.attech.estore.service.CustomerTransactionServices;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CustomerServicesImpl implements CustomerAccountServices, CustomerTransactionServices {
    private CustomerDao customerDao = new CustomerDao();

    @Override
    public  CustomerDto signUp(HttpServletRequest req, HttpServletResponse res) {
        CustomerDto customerDto1 = null;
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(req.getParameter("name"));
        customerDto.setEmail(req.getParameter("email"));
        customerDto.setGender(req.getParameter("gender"));
        customerDto.setAddress(req.getParameter("address"));
        customerDto.setPassword(req.getParameter("password"));
        customerDto.setWallet(0.00);
        try {
            customerDto1 = customerDao.saveCustomer(customerDto);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDto1;
    }

    @Override
    public CustomerDto logIn(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        return customerDao.findByUsernameAndPassword(email, password);
    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void updateProfile() {

    }

    @Override
    public void logOut() {

    }

    @Override
    public void addProductsToCart() {

    }

    @Override
    public void removeProductsFromCart() {

    }

    @Override
    public void checkOut() {

    }

    @Override
    public void cancelCheckOut() {

    }
}
