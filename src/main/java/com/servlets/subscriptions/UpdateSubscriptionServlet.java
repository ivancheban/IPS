package com.servlets.subscriptions;
import com.dto.SubscriptionDto;
import com.service.SubscriptionService;
import com.service.SubscriptionServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet(name = "findForUpdate", urlPatterns = "/update/subscription")
public class UpdateSubscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        String sId = request.getParameter("id");

        if (sId != null) {
            int id = Integer.parseInt(sId);
            SubscriptionDto subscriptionDto = subscriptionService.findById(id);
            request.getSession().setAttribute("subscriptionDto", subscriptionDto);
            response.sendRedirect("/update/form");
        } else {
            new ServletException("subscription did not found for update");

        }
    }
    }


