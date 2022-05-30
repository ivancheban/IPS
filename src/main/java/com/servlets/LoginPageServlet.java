package com.servlets;

import com.dao.UserDao;
import com.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/login"})
public class LoginPageServlet extends HttpServlet {

    private UserDao userDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone =req.getParameter("phone");
        String password =req.getParameter("password");
        User user = new User();
        user = userDao.findByField(phone);

      if (phone.equals(user.getPhone()) ){
            req.getRequestDispatcher("user-list.jsp").forward(req, resp);
        }
      else {
          req.getRequestDispatcher("error.html");
      }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
//
//        if (req.getParameter("sessionLocale") != null) {
//            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
//        }
//        chain.doFilter(request, response);
    }
    public void destroy() {}
    public void init(FilterConfig arg0) throws ServletException {}
}
