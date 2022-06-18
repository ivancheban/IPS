package com.servlets;

import com.exceptions.SubscriptionException;
import com.service.SubscriptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addTS",urlPatterns ="/add/tariff/service")
public class AddTariffForSubServlet extends HttpServlet {
    SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int sub_id = Integer.parseInt(req.getParameter("sub_id"));
        int tar_id = Integer.parseInt(req.getParameter("tar_id"));



        try {
            subscriptionService.addTariff(sub_id,tar_id);
            resp.sendRedirect("/tariffs");
        } catch (SubscriptionException e) {
            System.out.println("error" + e);
        }
    }
}
