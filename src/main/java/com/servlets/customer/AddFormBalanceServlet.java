package com.servlets.customer;

import com.dto.CustomerDto;
import com.dto.SubscriptionDto;
import com.service.CustomerService;
import com.service.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/customer/update/form")
public class AddFormBalanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/balance-update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        int balance = Integer.parseInt(request.getParameter("balance"));
        CustomerDto customerDto = new CustomerDto(id, balance);
        request.getSession().setAttribute("customerDto",customerDto);

        boolean result = customerService.addBalance(id, balance);
        if (result == true) {
            response.sendRedirect("/index.jsp");
        } else {
            new ServletException("did not updated balance for customer ");
        }
    }
}
