package com.servlets.customer;

import com.model.Customer;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        String phoneNumber = (String) req.getSession().getAttribute("phone");
        int id = customerService.findByPhoneNumber(phoneNumber).getId();
        Customer customer = customerService.findByPhoneNumber(phoneNumber);
        String balance = req.getParameter("balance");
        customerService.addBalance(id, Integer.parseInt(balance));
        int newBalance = customerService.findByPhoneNumber(phoneNumber).getBalance();
        req.getSession().setAttribute("balance", newBalance);

        resp.sendRedirect("/user/cabinet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/client/balance-update.jsp");

    }
}
