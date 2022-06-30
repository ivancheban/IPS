package com.servlets.customer;

import com.service.CustomerService;
import com.service.CustomerServiceImpl;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/tariff-customer")
public class DeleteTariffCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerServiceImpl();
        TariffService tariffService =new TariffServiceImpl();
        Integer custId = (Integer) req.getSession().getAttribute("customerId");
        String tarrId = req.getParameter("tariffs_id");
        System.out.println(tarrId);
        if (custId != null && tarrId != null) {
            int customerId = custId;
            int tariffId = Integer.parseInt(tarrId);
            customerService.deleteTariffCustomer(customerId, tariffId);
        }
        resp.sendRedirect("/user/cabinet");
    }
}
