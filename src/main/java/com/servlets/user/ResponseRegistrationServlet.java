package com.servlets.user;

import com.dto.CustomerCreateRequestDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "response_registration",urlPatterns = "/response_registration")
public class ResponseRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        List<String> userResponse = (List<String>) session.getAttribute("errorMessages");
        CustomerCreateRequestDto customerCreateRequestDto = (CustomerCreateRequestDto) session.getAttribute("customerCreateRequestDto");

        if (!userResponse.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/registration.jsp");
        }
        else {
            session.setAttribute("registrationMessage", "user with " + customerCreateRequestDto.getPhone() + " successful registered");
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
