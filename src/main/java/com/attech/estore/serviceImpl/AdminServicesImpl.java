package com.attech.estore.serviceImpl;


import com.attech.estore.dao.AdminDao;
import com.attech.estore.dto.ProductDto;
import com.attech.estore.service.AdminAccountServices;
import com.attech.estore.service.AdminProductServices;
import com.attech.estore.service.CustomerTransactionServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServicesImpl implements AdminAccountServices, CustomerTransactionServices, AdminProductServices {
    AdminDao adminDao = new AdminDao();
    @Override
    public void signUp() {

    }

    @Override
    public void logIn() {

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

    @Override
    public void addProducts(HttpServletRequest req, HttpServletResponse res) {
        ProductDto productDto = new ProductDto();
        productDto.setName(req.getParameter("name"));
        productDto.setPrice(Double.parseDouble(req.getParameter("price")));
        productDto.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        productDto.setUrl(req.getParameter("url"));
        String category =  req.getParameter("category");
        adminDao.insertProduct(productDto,category);
    }

    @Override
    public boolean removeProducts(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        return adminDao.deleteProduct(id);
    }

    @Override
    public boolean updateProducts(HttpServletRequest request, HttpServletResponse responce) {
        ProductDto productDto = new ProductDto();
        System.out.println(request.getParameter("id"));
        productDto.setId(Integer.parseInt(request.getParameter("id")));
        productDto.setName(request.getParameter("name"));
        productDto.setPrice(Double.parseDouble(request.getParameter("price")));
        productDto.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        productDto.setUrl(request.getParameter("url"));
        System.out.println(productDto);
        return adminDao.updateProduct(productDto);
    }

    @Override
    public void approvePurchase() {

    }
}
