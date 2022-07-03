package com.servlets.customer;

import com.dto.CustomerDto;
import com.dto.UserDto;
import com.service.CustomerService;
import com.service.CustomerServiceImpl;
import com.service.UserService;
import com.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update/profile/customer")
public class UpdateProfileCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        String customerID = req.getParameter("id");
        if (customerID != null) {
            int id = Integer.parseInt(customerID);
            CustomerDto customerDto = customerService.findDyID(id);
            req.getSession().setAttribute("customerDto", customerDto);

            resp.sendRedirect("/update-form/customer");
        }
        else new ServletException("customer did not found for update");
    }
}
