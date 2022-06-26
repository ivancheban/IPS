package com.servlets.subscriptions;

import com.model.Tariff;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/sort/tariffs")
public class SortingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImpl();
        List<Tariff> tariffs = (List<Tariff>) req.getSession().getAttribute("subscription");
        String sortBy = req.getParameter("sortBy");
        if(sortBy!= null){
            if(sortBy.equals("name")){
             tariffs=sortByName(tariffs);
            }
            if(sortBy.equals("price")){
                tariffs=sortByPrice(tariffs);
            }
        }
        req.getSession().setAttribute("subscription", tariffs);
        resp.sendRedirect("/service-page.jsp");

    }
    private List<Tariff> sortByName(List<Tariff> list){
        return list.stream().sorted(Comparator.comparing(Tariff::getName)).collect(Collectors.toList());
    }
    private List<Tariff> sortByPrice(List<Tariff> list){
        return list.stream().sorted(Comparator.comparing(Tariff::getPricePerDay)).collect(Collectors.toList());
    }
}
