package com.attech.estore.controller;

import com.attech.estore.dao.CustomerDao;
import com.attech.estore.dto.CustomerDto;
import com.attech.estore.service.CustomerAccountServices;
import com.attech.estore.serviceImpl.CustomerServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/pages/createCustomer")
public class customerAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        CustomerServicesImpl customerServices = new CustomerServicesImpl();
        CustomerDto customerDto = customerServices.signUp(req,res);
        if(customerDto != null ){
            HttpSession session = req.getSession();
            session.setAttribute("email", customerDto.getEmail());
            res.sendRedirect("./home.jsp");
        }else {
            out.println("handle me");
        }
    }
    }
