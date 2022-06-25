package com.servlets;
import com.dto.SubscriptionDto;
import com.model.Tariff;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "openPage", urlPatterns = "/open/service")
public class OpenPageService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        HttpSession session = req.getSession(true);
        String sid = req.getParameter("id");
        int id = Integer.valueOf(sid);

        SubscriptionDto subscriptionDto = subscriptionService.findById(id);
        List<Tariff> tariffList = subscriptionService.getAllByService(id);
        if (tariffList != null) {
            session.setAttribute("subscription", tariffList);
            session.setAttribute("subscriptionDto",subscriptionDto);
            resp.sendRedirect("/service-page.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        int sub_id = Integer.valueOf(request.getParameter("sub_id"));
        request.setAttribute("sub_id", sub_id);
        response.sendRedirect(request.getContextPath() + "/service-page.jsp");
    }
}
