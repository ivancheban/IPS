package com.servlets.tariff;

import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.mapper.BusinessMapper;
import com.model.ServiceType;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addTariff", urlPatterns = "/addTariff")
public class AddTariffServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(AddTariffServlet.class);
    private BusinessMapper businessMapper;

    private TariffService tariffService = new TariffServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("adding tariff");
        HttpSession session = req.getSession(true);

        String name = req.getParameter("name");
        ServiceType type = ServiceType.valueOf(req.getParameter("type"));
        int pricePerDay = Integer.parseInt(req.getParameter("price"));

        TariffDto tariffDto = new TariffDto(name, type, pricePerDay);

        try {


            if (tariffService.create(tariffDto)) {
                resp.sendRedirect("/tariffs");
            }

        } catch (TariffException e) {

            session.setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect("/admin.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
