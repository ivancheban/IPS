package com.servlets;

import com.dao.UserDao;
import com.dto.CustomerCreateRequestDto;
import com.model.User;
import com.service.CustomerService;
import com.service.CustomerServiceImpl;
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
import java.util.List;
import java.util.Map;

@WebServlet(name = "registration", urlPatterns = {"/user/registration"})
public class UserRegistrationServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserRegistrationServlet.class);

    private UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Customer registration");
        HttpSession session = req.getSession(true);
        PrintWriter message = resp.getWriter();


        resp.setContentType("text/html");
        String name = req.getParameter("name");
        System.out.println(name);

        String surname = req.getParameter("surname");
        System.out.println(surname);

        String email = req.getParameter("email");
        System.out.println(email);

        String phone = req.getParameter("phone");
        System.out.println(phone);

        String password = req.getParameter("password");
        System.out.println(password);

        String confirm_password = req.getParameter("confirm_password");
        System.out.println(confirm_password);


        CustomerCreateRequestDto customerCreateRequestDto = new CustomerCreateRequestDto(name,surname,email,phone, password,confirm_password);
        session.setAttribute("customerCreateRequestDto", customerCreateRequestDto);
        List<String> response =userService.registration(customerCreateRequestDto);

        session.setAttribute("errorMessages",response);
        resp.sendRedirect("/response_registration");




    }

}
