package com.servlets.subscriptions;

import com.dto.SubscriptionDto;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updatePost",urlPatterns = "/update/form")
public class UpdateSubServ extends HttpServlet {
    SubscriptionService subscriptionService = new SubscriptionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/subscription-update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        int days_amount = Integer.parseInt(request.getParameter("days_amount"));
        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));
        int id  = Integer.parseInt(request.getParameter("id"));

        SubscriptionDto subscriptionDto = new SubscriptionDto(id,name,days_amount,isActive);

        SubscriptionDto subNewDto = subscriptionService.update(subscriptionDto);
        if (subNewDto!= null){
            response.sendRedirect("/subscriptions");
        }
        else {
            new ServletException("did not updated ");
        }
    }
}
