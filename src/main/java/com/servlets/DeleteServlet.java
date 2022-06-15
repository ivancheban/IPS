package com.servlets;


import com.service.SubscriptionServiceImpl;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    private SubscriptionServiceImpl subcriptionService = new SubscriptionServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("begin");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("change");
        if (id != 0) {
            System.out.println("and");
            subcriptionService.delete(id);
            System.out.println("delete");
            RequestDispatcher view = request.getRequestDispatcher("subscriptions-list.jsp");

            view.forward(request, response);


        }
    }
}
