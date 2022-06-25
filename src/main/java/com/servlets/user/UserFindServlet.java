package com.servlets.user;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "findUser", urlPatterns = "/user/find")
public class UserFindServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    // private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");


    }
}
