package com.attech.estore.controller;

import com.attech.estore.dto.CustomerDto;
import com.attech.estore.serviceImpl.CustomerServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/pages/userAuthenticationServlet")
public class userAuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        CustomerServicesImpl customerServices = new CustomerServicesImpl();
        CustomerDto customer = customerServices.logIn(req, res);

        if(customer != null && customer.getRole().equals("admin")){
            HttpSession session = req.getSession();
            session.setAttribute("email", customer.getEmail());
            session.setAttribute("role", customer.getRole());
            res.sendRedirect("./dashboard.jsp");
        }
        else if(customer != null && customer.getRole().equals("customer")){
                HttpSession session = req.getSession();
                session.setAttribute("email", customer.getEmail());
                session.setAttribute("role", customer.getRole());
                res.sendRedirect("./home.jsp");
        }else {
            out.println("handle me");
        }
    }
}
