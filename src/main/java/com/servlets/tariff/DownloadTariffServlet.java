package com.servlets.tariff;

import com.model.Tariff;
import com.service.TariffService;
import com.service.TariffServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(urlPatterns = "/download/tariffs")
public class DownloadTariffServlet extends HttpServlet {
    TariffService tariffService = new TariffServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tariff> tariffs = tariffService.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Tariff tariff : tariffs) {
            stringBuilder.append(tariff);
            stringBuilder.append('\n');
        }
        File file = new File("C:/Users/eduar/IdeaProjects/IPS/src/main/webapp/WEB-INF/sample.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(stringBuilder);
        } finally {
            if (writer != null) writer.close();
        }

    }


}
