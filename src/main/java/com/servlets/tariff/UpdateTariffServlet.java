package com.servlets.tariff;

import com.dto.TariffDto;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/update/tariff")
public class UpdateTariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImpl();
       String tariffId = req.getParameter("id");
       if(tariffId!=null){
           int id = Integer.parseInt(tariffId);
           TariffDto tariffDto = tariffService.findById(id);
           req.getSession().setAttribute("tariffDto",tariffDto);
           resp.sendRedirect("/update-tariff/form");
       }
        else{
            new ServletException("tariff did not found for update");
       }
    }
}
