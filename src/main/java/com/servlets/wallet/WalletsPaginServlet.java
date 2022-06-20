package com.servlets.wallet;

import com.dao.SubscriptionDao;
import com.dao.WalletDao;
import com.model.Subscription;
import com.model.Wallet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "wallets", urlPatterns = "/wallets")
public class WalletsPaginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public WalletsPaginServlet() {
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

       WalletDao walletDao = null;
        try {
            walletDao = new WalletDao();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Wallet> walletsList = walletDao.getAll((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = walletDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("walletsList", walletsList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        RequestDispatcher view = req.getRequestDispatcher("wallet-list.jsp");
        view.forward(req, resp);
    }
}
