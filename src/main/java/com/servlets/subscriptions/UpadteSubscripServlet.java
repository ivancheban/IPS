package com.servlets.subscriptions;


import com.dao.SubscriptionDao;
import com.dto.SubscriptionDto;
import com.mapper.BusinessMapper;
import com.model.Subscription;
import com.model.User;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "update", urlPatterns = "/update/subscription")
public class UpadteSubscripServlet extends HttpServlet {
    private SubscriptionService subscriptionService = new SubscriptionServiceImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDao();
    private BusinessMapper businessMapper;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");

//        String oldName=request.getParameter("oldName");
//        //     Subscription subscription =subscriptionDao.findByField(String.valueOf(id));
//        String name=request.getParameter("name");
//        int days_amount = Integer.valueOf(request.getParameter("days_amount"));
//        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));
//
//        SubscriptionDto subscriptionDto = new SubscriptionDto();
//        Subscription status = subscriptionDao.updateSub(new Subscription(name, days_amount, isActive), oldName);
        String ids = request.getParameter("id");
        int id = Integer.valueOf(ids);
        Subscription subscription = subscriptionDao.findById(id);

        subscriptionDao.update(subscription);
        if (subscription != null) {
            response.sendRedirect("/subscriptions");
        } else {
            System.out.println("Sorry! unable to update record");
        }


    }
}


