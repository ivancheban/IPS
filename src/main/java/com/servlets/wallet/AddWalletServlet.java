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

    private WalletService walletService = new WalletServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("adding wallet");
        HttpSession session = req.getSession(true);

        String number = req.getParameter("number");
        System.out.println(number);
        double balance = Double.parseDouble(req.getParameter("balance"));
        System.out.println(balance);


        WalletDto walletDto= new WalletDto(number,balance);
        System.out.println("dto");

        try {
            if( walletService.create(walletDto)){

                resp.sendRedirect("/wallets");
            }
        } catch (WalletException e) {
            session.setAttribute("errorMessage", e.getMessage());
            System.out.println(e);
            resp.sendRedirect("/admin.jsp");
        }
    }
}
