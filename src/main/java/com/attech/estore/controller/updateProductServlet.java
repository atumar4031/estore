package com.attech.estore.controller;

import com.attech.estore.serviceImpl.AdminServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updateProductServlet", value = "/pages/updateProductServlet")
public class updateProductServlet extends HttpServlet {
    AdminServicesImpl adminServices = new AdminServicesImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(adminServices.updateProducts(req, res)){
            res.sendRedirect("./dashboard.jsp");
        }else {
            res.getWriter().println("data not updated");
        }
    }
}
