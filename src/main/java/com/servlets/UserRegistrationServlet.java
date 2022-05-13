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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "registration", urlPatterns = {"/user/registration"})
public class UserRegistrationServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserRegistrationServlet.class);

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("User registration");
        HttpSession session = req.getSession(true);
        PrintWriter message = resp.getWriter();


        resp.setContentType("text/html");

        String phone = req.getParameter("phone");
        System.out.println(phone);
        String password = req.getParameter("password");
        System.out.println(password);
        String confirm_password = req.getParameter("confirm_password");
        System.out.println(confirm_password);


        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(phone, password,confirm_password);
        System.out.println("1++++++++++++++++++++++++++++");
        Map<String, String> response = userService.registration(userCreateRequestDto);
        System.out.println("2++++++++++++++++++++++++++");

        if(!response.isEmpty()){
            session.setAttribute("errorMessages", response);
            System.out.println("============================");
            session.setAttribute("userCreateRequestDto", userCreateRequestDto);
            resp.sendRedirect(req.getContextPath() + "/registration.jsp");
        } else {
            session.setAttribute("registrationMessage", "user with " + userCreateRequestDto.getPhone() + " successful registered");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }

    }

}
