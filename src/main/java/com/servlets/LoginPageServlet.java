package com.servlets;

import com.dao.UserDao;
import com.model.User;
import com.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginPageServlet extends HttpServlet {

    private UserServiceImpl userServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userServiceImpl = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        User user = new User();
        user = userServiceImpl.findByPhoneNumber(phone);
        System.out.println(user);

        if (user.getPhone().equals(phone)) {
            req.getRequestDispatcher("success.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("error.html").forward(req, resp);
        }
    }



}
