package com.servlets;


import com.dao.TariffDao;


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

@WebServlet(name = "tariffList", urlPatterns = {"/tariffs"})
public class TariffPaginationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TariffPaginationServlet() {
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
        TariffDao tariffDao = null;
        try {
            tariffDao = new TariffDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Tariff> tariffList = tariffDao.getAll((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = tariffDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("tariffList", tariffList);
        req.setAttribute("tariffList", tariffList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        RequestDispatcher view = req.getRequestDispatcher("tariff-list.jsp");
        view.forward(req, resp);
    }
    }

