package com.servlets;

import com.dao.UserDao;
import com.dto.UserCreateRequestDto;
import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = {"/user/registration"})
public class UserRegistrationServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserRegistrationServlet.class);

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("User registration");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String conf_password = req.getParameter("conf_password");

        UserCreateRequestDto user = new UserCreateRequestDto(phone, password, conf_password);
        String response = userService.registration(user);


        if (!response.equals("successfully")) {
            req.setAttribute("validation_message", response);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);

        } else {
            req.setAttribute("validation_message", response);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);


        }

    }

}
