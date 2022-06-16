package com.servlets;


import com.dao.SubscriptionDao;
import com.dto.SubscriptionDto;
import com.mapper.BusinessMapper;
import com.model.Subscription;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "update", urlPatterns = "/update/subscription")
public class DeleteServlet extends HttpServlet {
    private SubscriptionService subscriptionService = new SubscriptionServiceImpl();
    private SubscriptionDao subscriptionDao = new SubscriptionDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        response.setContentType("text/html");


        String oldName=request.getParameter("oldName");
        System.out.println("old name ==> "+oldName);

//        Subscription subscription =subscriptionDao.findByField(String.valueOf(id));

        String name=request.getParameter("name");
        System.out.println(name);
        int days_amount = Integer.valueOf(request.getParameter("days_amount"));
        System.out.println(days_amount);
        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));
        System.out.println(isActive);



        SubscriptionDto subscriptionDto = new SubscriptionDto();

//        subscription.setId(id);
//        subscription.setName(name);
//        subscription.setDays(days_amount);
//        subscriptionDto.setActive(isActive);

        Subscription status = subscriptionDao.update(new Subscription(name, days_amount, isActive), oldName);
//        SubscriptionDto status = subscriptionService.update(subscriptionDto);
        if(status!= null){
            response.sendRedirect("/subscriptions");
        }else{
            System.out.println("Sorry! unable to update record");
        }



    }
    }


