package com.servlets.user;

import com.dto.UserDto;
import com.model.Role;
import com.service.UserService;
import com.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editUserPost", urlPatterns = "/edit/form")
public class UserEditServ extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/user-update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
        Role role = Role.valueOf(req.getParameter("role"));
        int id = Integer.parseInt(req.getParameter("id"));

        UserDto userDto =new UserDto(id,phone,password,isActive,role);
        UserDto editUser = userService.update(userDto);
        if (editUser!= null){
            resp.sendRedirect("/user.do");
        }
        else {
            new ServletException("did not updated ");
        }
    }
}
