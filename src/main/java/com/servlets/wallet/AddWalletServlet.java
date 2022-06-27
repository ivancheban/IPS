package com.servlets.wallet;

import com.dto.WalletDto;
import com.exceptions.WalletException;
import com.mapper.BusinessMapper;
import com.service.WalletService;
import com.service.WalletServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "wallet", urlPatterns = "/add/wallet")
public class AddWalletServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(AddWalletServlet.class);
    private BusinessMapper businessMapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WalletService walletService = new WalletServiceImpl();
        logger.info("adding wallet");
        HttpSession session = req.getSession(true);
        int id = Integer.parseInt(req.getParameter("id"));
        String number = req.getParameter("number");
        double balance = Double.parseDouble(req.getParameter("balance"));
        int customers_id = Integer.parseInt(req.getParameter("customers_id"));

        WalletDto walletDto = new WalletDto(id,number, balance,customers_id);
        try {
            if (walletService.create(walletDto)) {
                resp.sendRedirect("/wallets");
            }
        } catch (WalletException e) {
            session.setAttribute("errorMessage", e.getMessage());
            System.out.println(e);
            resp.sendRedirect("/admin.jsp");
        }
    }
}
