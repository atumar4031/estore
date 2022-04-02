package com.attech.estore.controller;

import com.attech.estore.serviceImpl.AdminServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet(value = "/pages/product")
@WebServlet(value = "/pages/addProduct")
public class ProductServlet extends HttpServlet {
    AdminServicesImpl adminServices = new AdminServicesImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        adminServices.addProducts(req, res);
        res.sendRedirect("./dashboard.jsp");

    }
}
