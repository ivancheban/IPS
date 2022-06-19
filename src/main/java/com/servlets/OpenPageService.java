package com.servlets;

import com.dao.SubscriptionDao;
import com.dto.SubscriptionDto;
import com.dto.TariffDto;
import com.model.Subscription;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "openPage", urlPatterns = "/open/service")
public class OpenPageService extends HttpServlet {
    SubscriptionService subscriptionService = new SubscriptionServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String sid = req.getParameter("id");
        int id = Integer.valueOf(sid);
        SubscriptionDto subscriptionDto = subscriptionService.findById(id);



        if(subscriptionDto!=null){
            session.setAttribute("subscription",subscriptionDto);
            resp.sendRedirect("/servicePage.jsp");
        }
        else {
            new SQLException("error");
        }

    }
}
