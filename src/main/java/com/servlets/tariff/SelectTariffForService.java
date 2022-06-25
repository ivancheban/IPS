package com.servlets.tariff;

import com.dto.SubscriptionDto;
import com.model.Tariff;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "select-tariff", urlPatterns = "/select/tariff")
public class SelectTariffForService extends HttpServlet {
    SubscriptionService service = new SubscriptionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int id = Integer.valueOf(req.getParameter("id"));
        SubscriptionDto subscriptionDto = service.findById(id);
    }
}
