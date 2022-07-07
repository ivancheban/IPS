package com.servlets.customer;

import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/download/price")
public class DownLoadPriceServicesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffService tariffService =new TariffServiceImpl();
        try {
            PrintWriter out = resp.getWriter();

            String filename = "/D:";
            FileWriter fw = new FileWriter(filename);


    }
catch (Exception ex) {
        ex.printStackTrace ();
    }
    }
}
