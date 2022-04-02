package com.attech.estore.controller;

import com.attech.estore.dao.AdminDao;
import com.attech.estore.serviceImpl.AdminServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteProductServlet", value = "/pages/deleteProductServlet")
public class deleteProductServlet extends HttpServlet {
  AdminServicesImpl adminServices = new AdminServicesImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(adminServices.removeProducts(req, res)){
         res.sendRedirect("./dashboard.jsp");
        }else {
            res.getWriter().println("product not deleted");
        }
    }
}
