package com.servlets.subscriptions;

import com.dao.SubscriptionDao;
import com.dao.TariffDao;
import com.dto.SubscriptionDto;
import com.dto.TariffDto;
import com.exceptions.SubscriptionException;
import com.mapper.BusinessMapper;
import com.model.Subscription;
import com.model.Tariff;
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

@WebServlet(name = "addTS", urlPatterns = "/add/tariff/service")
public class AddTariffForSubServlet extends HttpServlet {
    SubscriptionService subscriptionService = new SubscriptionServiceImpl();
    TariffService tariffService = new TariffServiceImpl();
    SubscriptionDao subscriptionDao = new SubscriptionDao();
    TariffDao tariffDao = new TariffDao();
    private BusinessMapper businessMapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int sub_id = Integer.parseInt(req.getParameter("sub_id"));
        int tar_id = Integer.parseInt(req.getParameter("tar_id"));


        Subscription subscription = subscriptionDao.findById(sub_id);
        Tariff tariff = tariffDao.findById(tar_id);
        SubscriptionDto subscriptionDto = BusinessMapper.getSubscriptionDto(subscription);
        TariffDto tariffDto = businessMapper.getTariffDto(tariff);

        try {
            subscriptionService.addTariff(sub_id, tar_id);
            session.setAttribute("subscriptionDto", subscriptionDto);
            session.setAttribute("tariffDto", tariffDto);
            resp.sendRedirect("/service-page.jsp");
        } catch (SubscriptionException e) {
            System.out.println("error" + e);
        }
    }
}
