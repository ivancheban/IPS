package com.servlets;

import com.dao.UserDao;
import com.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "userList", urlPatterns = {"/user.do"})
public class UserPaginationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserPaginationServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        UserDao userDao = null;
        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<User> list = userDao.getAll((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = userDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("userList", list);
        request.setAttribute("userList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("user-list.jsp");
        view.forward(request, response);
    }
}
