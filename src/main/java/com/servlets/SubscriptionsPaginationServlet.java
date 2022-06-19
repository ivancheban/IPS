package com.servlets;

import com.dao.SubscriptionDao;
import com.dao.TariffDao;
import com.exceptions.SubscriptionException;
import com.model.Subscription;
import com.model.Tariff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "subscriptions", urlPatterns = "/subscriptions")
public class SubscriptionsPaginationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SubscriptionsPaginationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        int page = 1;
        int recordsPerPage = 5;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        SubscriptionDao subscriptionDao = null;
        try {
            subscriptionDao = new SubscriptionDao();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        List<Subscription> subscriptionsList = subscriptionDao.getAll((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = subscriptionDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("subscriptionsList", subscriptionsList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        RequestDispatcher view = req.getRequestDispatcher("subscriptions-list.jsp");
        view.forward(req, resp);
    }

}
