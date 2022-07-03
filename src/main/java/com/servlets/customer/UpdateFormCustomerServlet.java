package com.servlets.customer;

import com.dto.CustomerCreateRequestDto;
import com.dto.CustomerDto;
import com.dto.UserDto;
import com.model.Customer;
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

@WebServlet(urlPatterns = "/update-form/customer")
public class UpdateFormCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/customer-update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phoneNumber = req.getParameter("phone");
        String email = req.getParameter("email");
        int id = Integer.parseInt(req.getParameter("id"));

       CustomerDto customerDto = new CustomerDto(id, surname, name, phoneNumber, email);
       CustomerDto editCustomer = customerService.updateProfile(customerDto);


        if (editCustomer != null) {

            resp.sendRedirect("/user/cabinet");
        } else {
            new ServletException("customer did not updated ");
        }

    }
}
