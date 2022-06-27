package com.servlets.customer;

import com.dto.CustomerDto;

import com.service.CustomerService;
import com.service.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add/balance")
public class AddBalanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        String customerId = req.getParameter("id");
        if (customerId != null) {
            int id = Integer.parseInt(customerId);
            CustomerDto customerDto = customerService.findDyID(id);
            req.getSession().setAttribute("customerDto", customerDto);
            resp.sendRedirect("/customer/update/form");
        } else {
            new ServletException("customer did not found for update");
        }
      }
}
