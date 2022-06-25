package com.servlets.user;

import com.dto.UserDto;
import com.service.UserService;
import com.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editUser", urlPatterns = "/user/update")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String sId = request.getParameter("id");

        if (sId != null) {
            int id = Integer.parseInt(sId);
            UserDto userDto = userService.findById(id);
            request.getSession().setAttribute("userDto", userDto);
            response.sendRedirect("/edit/form");
        } else {
            new ServletException("user did not found for update");

        }
    }
}
