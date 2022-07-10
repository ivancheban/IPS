package com.servlets.tariff;

import com.dto.TariffDto;
import com.model.ServiceType;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update-tariff/form")
public class UpdateFormTariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/tariff-update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImpl();
        String name = req.getParameter("name");
        String serviceType = req.getParameter("service_type");
        String price = req.getParameter("price_per_day");
        boolean status = Boolean.parseBoolean(req.getParameter("isActive"));
        String tariffId = req.getParameter("id");
        int id = Integer.parseInt(tariffId);
        ServiceType type = ServiceType.valueOf(serviceType);
        int priceTariff = Integer.parseInt(price);


        TariffDto tariffDto = new TariffDto(id,name, type, priceTariff,status);
        System.out.println(tariffDto);
        TariffDto editTariff = tariffService.update(tariffDto);
        System.out.println(editTariff);
        if (editTariff != null) {

            resp.sendRedirect("/tariffs");
        }
        else new ServletException("tariff did not updated ");
    }
}
