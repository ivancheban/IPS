package com.servlets.customer;

import com.service.CustomerService;
import com.service.CustomerServiceImpl;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add/tariff/customer")
public class AddTariffCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        TariffService tariffService =new TariffServiceImpl();
        String custId = req.getParameter("customer_id");
        System.out.println(custId);
        String tarrId = req.getParameter("tariff_id");
        System.out.println(tarrId);
        int price = tariffService.findById(Integer.parseInt(tarrId)).getPricePerDay();
        if (custId != null && tarrId != null) {
            int customerId = Integer.parseInt(custId);
            int tariffId = Integer.parseInt(tarrId);
            customerService.addTariffCustomer(customerId, tariffId);

            customerService.withdrawBalance(customerId, price);
        }

            resp.sendRedirect("/user/cabinet");

        }
    }

