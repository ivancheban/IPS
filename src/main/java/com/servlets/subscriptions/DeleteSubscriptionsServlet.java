package com.servlets.subscriptions;

import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete",urlPatterns = "/delete/subscription")
public class DeleteSubscriptionsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubscriptionService subscriptionService =new SubscriptionServiceImpl();
        String sid=request.getParameter("id");
        int id=Integer.valueOf(sid);
        subscriptionService.delete(id);
        response.sendRedirect("/subscriptions");

    }
}
