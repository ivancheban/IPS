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
        String tarId = req.getParameter("tariff_id");

        int price = tariffService.findById(Integer.parseInt(tarId)).getPricePerDay();
        if (custId != null && tarId != null) {
            int customerId = Integer.parseInt(custId);
            int tariffId = Integer.parseInt(tarId);
            int balance = customerService.findDyID(customerId).getBalance();
            System.out.println(balance);
            boolean isSubscribe = customerService.checkSubscription(customerId, tariffId);
            if(balance<price) {
                req.getSession().setAttribute("SubscriptionErrorMessage","NotEnoughMoney");
            } else if (isSubscribe){
                req.getSession().setAttribute("SubscriptionErrorMessage","youAreAlreadySubscribed");
            } else {
                customerService.addTariffCustomer(customerId, tariffId);
                customerService.withdrawBalance(customerId, price);
            }
        }

            resp.sendRedirect("/user/cabinet");

        }
    }

