//package com.filters;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/user-list.jsp")
//public class AdminAuthenticationFilter implements Filter {
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession(false);
//
//        boolean isLoggedIn = (session != null && session.getAttribute("adminUser") != null);
//
//        String loginURI = httpRequest.getContextPath() + "/admin/login";
//
//        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
//
//        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
//
//        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
//            // the admin is already logged in and he's trying to login again
//            // then forwards to the admin's homepage
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
//            dispatcher.forward(request, response);
//
//        } else if (isLoggedIn || isLoginRequest) {
//            // continues the filter chain
//            // allows the request to reach the destination
//            chain.doFilter(request, response);
//
//        } else {
//            // the admin is not logged in, so authentication is required
//            // forwards to the Login page
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
//
//        }
//
//    }
//
//    public void AdminLoginFilter() {
//    }
//
//    public void destroy() {
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//    }
//
//}
