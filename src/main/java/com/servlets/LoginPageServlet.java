package com.servlets;

import com.auth.JWTService;
import com.dao.UserDao;
import com.model.Role;
import com.model.User;
import com.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/login"})
public class LoginPageServlet extends HttpServlet {
    private JWTService jwtService;
    private UserServiceImpl userServiceImpl;


    @Override
    public void init(ServletConfig config) throws ServletException {

        this.userServiceImpl = new UserServiceImpl();
        jwtService = new JWTService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");


        Role userRole = userServiceImpl.identy(phone, password);
        if (userRole != null) {
            Map<String, String> infoTable = userTableInfo(phone, password);
            String token = jwtService.createToken(infoTable, 1);

            ServletContext context = request.getServletContext();
            context.setAttribute(token, token);
            request.getSession().setAttribute("token", token);

            if (userRole.equals(Role.ADMIN)) {
                response.sendRedirect("/admin.jsp");
            } else if (userRole.equals(Role.CLIENT)) {
                response.sendRedirect("/subscriptions");

            }
        }else {
            request.getSession().setAttribute("loginError", "Wrong email or password");
            response.sendRedirect("/login");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/logIn.jsp");
    }
    private Map<String, String> userTableInfo(String phoneNumber, String password) {
        Map<String, String> infoTable = new HashMap<String, String>();
        infoTable.put("phoneNumber", phoneNumber);
        infoTable.put("password", password);
        return infoTable;
    }


}
